package railway.bj.admin.my.job.ui;

import java.sql.Timestamp;

import jodd.datetime.JDateTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.hibernate.Session;

import railway.bj.admin.my.job.dao.entity.Doccatalog;
import railway.bj.admin.my.job.dao.entity.DoccatalogDAO;
import railway.bj.admin.my.job.dao.entity.Docrecv;
import railway.bj.admin.my.job.dao.entity.DocrecvDAO;

public class RecvDocInput extends Composite {
	private Button btnCheckButton;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private List list,list_1;
	private Label lblNewLabel_6;
	private Session session;
	private Doccatalog cata;
	
	public void setDoccatalog(Doccatalog cata,Session session){
		this.cata = cata;
		this.session = session;
		text.setText(cata.getDocid()+"");
		text_1.setText(cata.getDoccaption()+""+cata.getDoccode()+"");
		text_2.setText(list.getItem(0).toString().replaceAll(" ", "").trim());
		if (text_3!=null) text_3.setText(new JDateTime(System.currentTimeMillis()).toString("YYYYMMDD"));
		text_4.setText("");
		text_5.setText("");
		if (text_6!=null) text_6.setText(new JDateTime(System.currentTimeMillis()).toString("YYYYMMDD"));
		lblNewLabel_6.setText("");
		btnCheckButton.setSelection(false);
	}

	/**
	 * 入库；
	 */
	private void save(){
		try{
			session.beginTransaction();
			DocrecvDAO recvdao = new DocrecvDAO();
			Docrecv  recv = new Docrecv();
			recv.setDocid(cata.getDocid());
			recv.setTransmitter(text_2.getText());
			recv.setRecvdate(Integer.parseInt(new JDateTime(text_3.getText(),"YYYYMMDD").toString("YYYYMMDD")));
			recv.setOpertimestamp(new Timestamp(System.currentTimeMillis()));
			recv.setRecvTag(text_4.getText());
			recv.setMemo(text_5.getText());
			recv.setTriggertime(text_6.getText());//可以尽早
			recv.setIsok(btnCheckButton.getSelection());
			recvdao.save(recv);
			
			DoccatalogDAO catadao = new DoccatalogDAO();
			cata.setIshidden(true);
			catadao.merge(cata);
			
			session.getTransaction().commit();
			System.out.println("保存"+cata.getDocid()+"-"+cata.getDoccode()+" 成功!");
			lblNewLabel_6.setText("保存"+cata.getDoccode()+" 成功!");
		}catch(Exception ex){
			ex.printStackTrace();
			lblNewLabel_6.setText("保存"+cata.getDoccode()+" 失败!");
		}
	}
	
	private void ini(){
	    if (text_3!=null) text_3.setText(new JDateTime(System.currentTimeMillis()).toString("YYYYMMDD"));
		if (text_6!=null) text_6.setText(new JDateTime(System.currentTimeMillis()).toString("YYYYMMDD"));
	}
	private void add1day(){
		JDateTime jdt = new JDateTime(text_3.getText(),"YYYYMMDD");
		jdt.addDay(1);
		text_3.setText(jdt.toString("YYYYMMDD"));
	}
	
	private void subtract1day(){
		JDateTime jdt = new JDateTime(text_3.getText(),"YYYYMMDD");
		jdt.subDay(1);
		text_3.setText(jdt.toString("YYYYMMDD"));
	}

	private void selectTransmitter(){
		String temp = "";
		if (list.getSelection()!=null){
			temp = list.getSelection()[0].toString().replaceAll(" ", "").trim();
			text_2.setText(text_2.getText().length()==0?temp:text_2.getText()+"、"+temp);
		}
	}
	
	private void selectTag(){
		String temp = "";
		if (list_1.getSelection()!=null){
			temp = list_1.getSelection()[0].toString().replaceAll(" ", "").trim();
			text_4.setText(text_4.getText().length()==0?temp:text_4.getText()+"、"+temp);
		}
	}
	
	private void clear(){
		text_4.setText("");
	}
	private void clear2(){
		text_2.setText("");
	}
	private void clear3(){
		text_5.setText("");
	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RecvDocInput(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		lblNewLabel_6 = new Label(this, SWT.NONE);
		lblNewLabel_6.setText("0");
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_6.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.NORMAL));
		GridData gd_lblNewLabel_6 = new GridData(SWT.FILL, SWT.TOP, true, false, 3, 1);
		gd_lblNewLabel_6.heightHint = 40;
		lblNewLabel_6.setLayoutData(gd_lblNewLabel_6);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("文件ID号");
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("主要内容");
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
		text_1.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		GridData gd_text_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_1.heightHint = 71;
		text_1.setLayoutData(gd_text_1);
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("传 达 人");
		
		text_2 = new Text(this, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		text_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				clear2();
			}
		});
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_text_2.heightHint = 74;
		gd_text_2.widthHint = 166;
		text_2.setLayoutData(gd_text_2);
		
		list =  new List(this, SWT.BORDER | SWT.V_SCROLL);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTransmitter();
			}
		});
		list.setItems(new String[] {"李  伟", "范久顺", "吴文楠", "转  交"});
		GridData gd_list = new GridData(SWT.LEFT, SWT.FILL, true, false, 1, 1);
		gd_list.widthHint = 87;
		list.setLayoutData(gd_list);
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("接收日期");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group group = new Group(this, SWT.NONE);
		group.setLayout(new GridLayout(2, false));
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				subtract1day();
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		btnNewButton.setText("向前");
		
		Button btnNewButton_1 = new Button(group, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				add1day();
			}
		});
		btnNewButton_1.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		btnNewButton_1.setText("向后");
		
		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_4.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("标  签");
		
		text_4 = new Text(this, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
		text_4.setEditable(true);
		text_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				clear();
			}
		});
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		GridData gd_text_4 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_text_4.heightHint = 108;
		text_4.setLayoutData(gd_text_4);
		
		Group group_1 = new Group(this, SWT.NONE);
		group_1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		group_1.setLayout(new GridLayout(1, false));
		
		list_1 = new List(group_1, SWT.BORDER);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTag();
			}
		});
		list_1.setItems(new String[] {"[无]", "高铁", "专用线"});
		GridData gd_list_1 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_list_1.widthHint = 56;
		list_1.setLayoutData(gd_list_1);
		
		Label lblNewLabel_7 = new Label(this, SWT.NONE);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_7.setText("执行日期");
		
		text_6 = new Text(this, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group group_2 = new Group(this, SWT.NONE);
		group_2.setLayout(new GridLayout(2, false));
		group_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button btnNewButton_3 = new Button(group_2, SWT.NONE);
		btnNewButton_3.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		btnNewButton_3.setText("向前");
		
		Button btnNewButton_4 = new Button(group_2, SWT.NONE);
		btnNewButton_4.setFont(SWTResourceManager.getFont("宋体", 11, SWT.NORMAL));
		btnNewButton_4.setText("向后");
		
		Label lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		lblNewLabel_5.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_5.setText("备  注");
		
		text_5 = new Text(this, SWT.BORDER | SWT.WRAP);
		text_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				clear3();
			}
		});
		text_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		GridData gd_text_5 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_5.heightHint = 78;
		text_5.setLayoutData(gd_text_5);
		new Label(this, SWT.NONE);
		
		btnCheckButton = new Button(this, SWT.CHECK);
		btnCheckButton.setText("办完");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		//new Label(this, SWT.NONE);
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				save();
			}
		});
		btnNewButton_2.setFont(SWTResourceManager.getFont("宋体", 14, SWT.NORMAL));
		GridData gd_btnNewButton_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_btnNewButton_2.heightHint = 55;
		btnNewButton_2.setLayoutData(gd_btnNewButton_2);
		btnNewButton_2.setText("保存");
		
		//20140108
		ini();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
