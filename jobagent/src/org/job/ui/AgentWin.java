package org.job.ui;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.job.agent.JobAgent;
import org.job.agent.interf.INotifyDisconnect;
import org.job.agent.interf.INotifyObject;
import org.job.task.ActiveMonitor;

public class AgentWin implements INotifyObject,INotifyDisconnect{

	protected Shell shlAgent;
	private Text text;
	private Text text_1;
	private List list;
	private Combo combo;
	private StyledText styledText;
	private Text text_2;
	private Text txtC;
	
	private void init(){
		String commands[]= new String[4];
		commands[0]="SYSSTOP";
		commands[1]="FA";
		commands[2]="FO";
		commands[3]="SYSPROP";	//取得系统属性等信息
//		commands[2]="SYS";
//		commands[3]="SYS";
//		commands[4]="SYS";
//		commands[5]="SYS";
//		commands[6]="SYS";
//		commands[7]="SYS";
		combo.setItems(commands);
	}
	
	private void send1(){
		try {
			String txt = text_1.getText().toString().trim();
			String threadid = txt.substring((txt.indexOf("[")+1),txt.indexOf("]"));
			Chat chat = JobAgent.chatProcess.getCMC(threadid).getChat();
			Message msgcmd = new Message();
			msgcmd.setProperty("MSGTYPE","CMD");
			msgcmd.setBody(combo.getText());
			boolean f = combo.getText().toString().trim().toUpperCase().equals("FA");
			if (f){
				msgcmd.setProperty("FPORT",text_2.getText().trim());
				msgcmd.setProperty("FDIR",txtC.getText().trim());
			}
			if (f && (text_2.getText()==null ||  txtC.getText()==null)){
				System.out.println("f-x");
				return;
			}
			chat.sendMessage(msgcmd);
		} catch (XMPPException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void send2(){
		try {
			String txt = text_1.getText().toString().trim();
			String threadid = txt.substring((txt.indexOf("[")+1),txt.indexOf("]"));
			Chat chat = JobAgent.chatProcess.getCMC(threadid).getChat();
			Message msgcmd = new Message();
			msgcmd.setBody(text.getText());
			msgcmd.setProperty("MSGTYPE","INFO");
			chat.sendMessage(msgcmd);
		} catch (XMPPException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void selectclient(){
		if (list!=null){
			if (list.getSelection().length>0){
				String client = list.getSelection()[0].toString().trim();
				if (text_1!=null) text_1.setText(client);
				System.out.println(""+client.substring(0, client.indexOf("/")));
				
				String txt = text_1.getText().toString().trim();
				showMessage(txt.substring((txt.indexOf("[")+1),txt.indexOf("]")));
			}
		}
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JobAgent jobAgent = new JobAgent("10.64.145.245", "Hui-PC", "agent", "bdesdk", ""); //Compaq-PC
			AgentWin window = new AgentWin();
			JobAgent.chatProcess.addListener(window);////加上消息的监听 
			ActiveMonitor am = new ActiveMonitor();
			am.addListener(window);//加上断开连接的监听 
			new Thread(am).start();
			jobAgent.start();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyDisconnect(String threadid) {
		Display.getDefault().syncExec(new DisconnectTip(threadid));
	}
	
	@Override
	public void notifyChat() {
		uiChatChange();
	}

	@Override
	public void notifyMessage() {
		Display.getDefault().syncExec(new MessageTip());
	}
	
	public void uiChatChange(){
		Display.getDefault().syncExec(new ChatTip());
	}
	
	@SuppressWarnings("unchecked")
	void showMessage(String threadid){
		Vector<Message> v = JobAgent.chatProcess.getCMC(threadid).getVmesg();
		if (styledText!=null){
			styledText.setText("");
			for (int i=0;i<v.size();i++){
				styledText.insert("\n"+ " " +v.elementAt(i)==null?"":v.elementAt(i).getBody().toString().trim());
				styledText.insert("\n");
				Iterator it= v.elementAt(i).getPropertyNames().iterator();
				while (it.hasNext()){Object o = it.next();if (o!=null) styledText.insert("\n"+o.toString()+":"+v.elementAt(i).getProperty(o.toString().trim()));}
			}
		}
	}
	
	class DisconnectTip implements Runnable{
		public DisconnectTip(String threadid) {
			super();
			this.threadid = threadid;
		}
		String threadid = null;
		@Override
		public void run() {
			try{
				JobAgent.chatProcess.removeCMC(threadid);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	class MessageTip implements Runnable{
		@Override
		public void run() {
			try{
				if (text_1.getText()!=null||text_1.getText().length()>0){
					String txt = text_1.getText().toString().trim();
					System.out.println("chats:"+txt);
					if (txt.length()>0){
						String threadid = txt.substring((txt.indexOf("[")+1),txt.indexOf("]"));
						showMessage(threadid);
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	class ChatTip implements Runnable{
		private Vector v = null;
		@Override
		public void run() {
			Vector v = JobAgent.chatProcess.getThreadCollection();
			System.out.println("chats:"+v.size());
			if (list!=null){
				list.removeAll();
				for (int i=0;i<v.size();i++){
					String threadid = v.get(i).toString();
					list.add(JobAgent.chatProcess.getCMC(threadid).getParticipant()+"["+threadid+"]");
				}
			}
		}
	}
	
	protected void setScreenPoint(Shell shell) {
		int width = shell.getMonitor().getClientArea().width;
		int height = shell.getMonitor().getClientArea().height;
		int x = shell.getSize().x;
		int y = shell.getSize().y;
		if (x > width) {
			shell.getSize().x = width;
		}
		if (y > height) {
			shell.getSize().y = height;
		}
		shell.setLocation((width - x) / 2, (height - y) / 2);
	}

	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		setScreenPoint(shlAgent);
		shlAgent.setLayout(new GridLayout(7, false));
		
		Menu menu = new Menu(shlAgent, SWT.BAR);
		shlAgent.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.CASCADE);
		mntmNewItem.setText("系统");
		
		Menu menu_1 = new Menu(mntmNewItem);
		mntmNewItem.setMenu(menu_1);
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.setText("退出");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlAgent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2);
		gd_scrolledComposite.heightHint = 177;
		gd_scrolledComposite.widthHint = 372;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		list = new List(scrolledComposite, SWT.BORDER);
		list.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectclient();
			}
		});
		scrolledComposite.setContent(list);
		scrolledComposite.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		text_1 = new Text(shlAgent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 6, 1));
		
		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(shlAgent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 6, 1);
		gd_scrolledComposite_1.heightHint = 150;
		gd_scrolledComposite_1.widthHint = 430;
		scrolledComposite_1.setLayoutData(gd_scrolledComposite_1);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		styledText = new StyledText(scrolledComposite_1, SWT.BORDER);
		scrolledComposite_1.setContent(styledText);
		scrolledComposite_1.setMinSize(styledText.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		new Label(shlAgent, SWT.NONE);
		
		combo = new Combo(shlAgent, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(shlAgent, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("port");
		
		text_2 = new Text(shlAgent, SWT.BORDER);
		text_2.setText("1234");
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 56;
		text_2.setLayoutData(gd_text_2);
		
		Label lblNewLabel_1 = new Label(shlAgent, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("dir");
		
		txtC = new Text(shlAgent, SWT.BORDER);
		txtC.setText("c:\\");
		GridData gd_txtC = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtC.widthHint = 47;
		txtC.setLayoutData(gd_txtC);
		
		Button btnNewButton = new Button(shlAgent, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				send1();
			}
		});
		btnNewButton.setText("发送1");
		new Label(shlAgent, SWT.NONE);
		
		text = new Text(shlAgent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 5, 1));
		
		Button btnNewButton_1 = new Button(shlAgent, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				send2();
			}
		});
		btnNewButton_1.setText("发送2");
		
		init();//20140213

		shlAgent.open();
		shlAgent.layout();
		while (!shlAgent.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAgent = new Shell();
		shlAgent.setSize(800, 615);
		shlAgent.setText("链接管理器");
	}


}
