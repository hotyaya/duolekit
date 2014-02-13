package org.job.ui;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
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
import org.job.agent.JobAgent;
import org.job.agent.interf.INotifyObject;

public class AgentWin implements INotifyObject{

	protected Shell shlAgent;
	private Text text;
	private Text text_1;
	private Text text_2;
	private List list;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JobAgent jobAgent = new JobAgent("10.64.145.245", "Hui-PC", "agent", "bdesdk", ""); //Compaq-PC
			AgentWin window = new AgentWin();
			JobAgent.chatProcess.addListener(window);
			jobAgent.start();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void notifyChat() {
		uiChatChange();
	}

	@Override
	public void notifyMessage() {
		
	}
	
	public void uiChatChange(){
		Display.getDefault().syncExec(new ChatTip());
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
					list.add(v.get(i).toString());
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
		shlAgent.setLayout(new GridLayout(3, false));
		
		Menu menu = new Menu(shlAgent, SWT.BAR);
		shlAgent.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.CASCADE);
		mntmNewItem.setText("系统");
		
		Menu menu_1 = new Menu(mntmNewItem);
		mntmNewItem.setMenu(menu_1);
		
		MenuItem mntmNewItem_1 = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem_1.setText("退出");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlAgent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2);
		gd_scrolledComposite.heightHint = 177;
		gd_scrolledComposite.widthHint = 185;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		list = new List(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(list);
		scrolledComposite.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		text_1 = new Text(shlAgent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shlAgent, SWT.NONE);
		
		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(shlAgent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_scrolledComposite_1.heightHint = 150;
		gd_scrolledComposite_1.widthHint = 430;
		scrolledComposite_1.setLayoutData(gd_scrolledComposite_1);
		scrolledComposite_1.setExpandHorizontal(true);
		scrolledComposite_1.setExpandVertical(true);
		
		StyledText styledText = new StyledText(scrolledComposite_1, SWT.BORDER);
		scrolledComposite_1.setContent(styledText);
		scrolledComposite_1.setMinSize(styledText.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		new Label(shlAgent, SWT.NONE);
		new Label(shlAgent, SWT.NONE);
		
		Combo combo = new Combo(shlAgent, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(shlAgent, SWT.NONE);
		btnNewButton.setText("发送1");
		new Label(shlAgent, SWT.NONE);
		
		text = new Text(shlAgent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton_1 = new Button(shlAgent, SWT.NONE);
		btnNewButton_1.setText("发送2");
		
		text_2 = new Text(shlAgent, SWT.BORDER);
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1);
		gd_text_2.heightHint = 35;
		text_2.setLayoutData(gd_text_2);
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
		shlAgent.setSize(671, 361);
		shlAgent.setText("Agent代理");

	}

}
