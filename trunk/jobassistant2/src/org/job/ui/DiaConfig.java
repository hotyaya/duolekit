package org.job.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.HibernateUtil;
import org.job.dao.entity.Sysprop;
import org.job.dao.entity.SyspropDAO;

public class DiaConfig extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Button btnCheckButton;
	private Button btnCheckButton_1;
	private Button btnCheckButton_2;
	private Button btnCheckButton_3;

	private Sysprop pro1 = null;
	private Sysprop  pro2 = null;
	private Sysprop  pro3 = null;
	private Sysprop  pro4 = null;
	private Sysprop  pro5 = null;
	private Sysprop  pro6 = null;
	private Sysprop  pro7 = null;
	private Sysprop  pro8 = null;
	private Sysprop  pro9 = null;
	private Sysprop  pro10 = null;
	private Sysprop  pro11 = null;
	private Sysprop  pro12 = null;
	private Sysprop  pro13 = null;
	private Sysprop  pro14 = null;
	
	void init0(){
		pro1 = null;
		pro2 = null;
		pro3 = null;
		pro4 = null;
		pro5 = null;
		pro6 = null;
		pro7 = null;
		pro8 = null;
		pro9 = null;
		pro10 = null;
		pro11 = null;
		pro12 = null;
		pro13 = null;
		pro14 = null;	
	}
	
	/**
	pro1 = dao.findById("USERID");
	pro2 = dao.findById("USERNAME");
	pro3 = dao.findById("USERUNIT");
	pro4 = dao.findById("USERDEPT");
	pro5 = dao.findById("OADO");
	pro6 = dao.findById("OAUSER");
	pro7 = dao.findById("OAPASS");
	pro8 = dao.findById("TGDO");
	pro9 = dao.findById("TGUSER");
	pro10 = dao.findById("TGPASS");
	pro11 = dao.findById("DBDO");
	pro12 = dao.findById("DBUSER");
	pro13 = dao.findById("DBPASS");
	 */
	void save(){
		try{
			Session session = HibernateUtil.currentSession();
			Transaction tran = null;
			tran = session.beginTransaction();
			if (pro1!=null){
				session.merge(new Sysprop("USERID",text.getText()));
			}else{
				session.save(new Sysprop("USERID",text.getText()));
			}
			if (pro2!=null){
				session.merge(new Sysprop("USERNAME",text_1.getText()));
			}else{
				session.save(new Sysprop("USERNAME",text_1.getText()));
			}
			if (pro3!=null){
				session.merge(new Sysprop("USERUNIT",text_2.getText()));
			}else{
				session.save(new Sysprop("USERUNIT",text_2.getText()));
			}
			if (pro4!=null){
				session.merge(new Sysprop("USERDEPT",text_3.getText()));
			}else{
				session.save(new Sysprop("USERDEPT",text_3.getText()));
			}
			if (pro5!=null){
				session.merge(new Sysprop("OADO",btnCheckButton_1.getSelection()?"TRUE":"FALSE"));
			}else{
				session.save(new Sysprop("OADO",btnCheckButton_1.getSelection()?"TRUE":"FALSE"));
			}
			if (pro6!=null){
				session.merge(new Sysprop("OAUSER",text_4.getText()));
			}else{
				session.save(new Sysprop("OAUSER",text_4.getText()));
			}
			if (pro7!=null){
				session.merge(new Sysprop("OAPASS",text_5.getText()));
			}else{
				session.save(new Sysprop("OAPASS",text_5.getText()));
			}
			if (pro8!=null){
				session.merge(new Sysprop("TGDO",btnCheckButton.getSelection()?"TRUE":"FALSE"));
			}else{
				session.save(new Sysprop("TGDO",btnCheckButton.getSelection()?"TRUE":"FALSE"));
			}
			if (pro9!=null){
				session.merge(new Sysprop("TGUSER",text_6.getText()));
			}else{
				session.save(new Sysprop("TGUSER",text_6.getText()));
			}
			if (pro10!=null){
				session.merge(new Sysprop("TGPASS",text_7.getText()));
			}else{
				session.save(new Sysprop("TGPASS",text_7.getText()));
			}
			if (pro11!=null){
				session.merge(new Sysprop("DBDO",btnCheckButton_2.getSelection()?"TRUE":"FALSE"));
			}else{
				session.save(new Sysprop("DBDO",btnCheckButton_2.getSelection()?"TRUE":"FALSE"));
			}
			if (pro12!=null){
				session.merge(new Sysprop("DBUSER",text_8.getText()));
			}else{
				session.save(new Sysprop("DBUSER",text_8.getText()));
			}
			if (pro13!=null){
				session.merge(new Sysprop("DBPASS",text_9.getText()));
			}else{
				session.save(new Sysprop("DBPASS",text_9.getText()));
			}
			if (pro14!=null){
				session.merge(new Sysprop("STARTCONFIG",btnCheckButton_3.getSelection()?"TRUE":"FALSE"));
			}else{
				session.save(new Sysprop("STARTCONFIG",btnCheckButton_3.getSelection()?"TRUE":"FALSE"));
			}
			//session.save(item);
			tran.commit();
			new AutoCloseDialog(shell, AutoCloseDialog.INFORMATION, "保存成功！", null , 2000l).open();
			Thread.sleep(100);
		}catch(Exception ex){
			new AutoCloseDialog(shell, AutoCloseDialog.INFORMATION, ex.toString(), null , 2000l).open();
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
		init();//重新初始化下！
	}
	
	/**
	 * 
	 * 	1.USERID		UUID
		2.USERNAME		NAME
		3.USERUNIT		使用者所在单位；（单位）
		4.USERDEPT		使用者所在单位；（部门）
		5.OADO			登录帐号不同；boolean
		6.OAUSER		登录帐号不同；xxhclh
		7.OAPASS		登录帐号不同；a
		8.TGDO			登录帐号不同；boolean
		9.TGUSER		登录帐号不同；zhanglan
		10.TGPASS		登录帐号不同；a
		11.DBDO			登录帐号不同；boolean
		12.DBUSER		登录帐号不同；user
		13.DBPASS		登录帐号不同；pass
	 * 	text			ID
	 * 	text_1			USER
	 *	text_2			单位
		text_3			部门
		btnCheckButton 	启用电报追踪
		text_4			电报用户
		text_5			电报密码
		btnCheckButton_1 公文追踪
		text_6			公文用户
		text_7			公文密码
		btnCheckButton_2 待办
		text_8			待办用户
		text_9			待办密码
		btnCheckButton_3 启动时显示 
	 */
	void init(){
		try{
			init0();
			Session session = HibernateUtil.currentSession();
			Transaction tran = session.beginTransaction();
			SyspropDAO dao =new SyspropDAO();
			pro1 = dao.findById("USERID");
			pro2 = dao.findById("USERNAME");
			pro3 = dao.findById("USERUNIT");
			pro4 = dao.findById("USERDEPT");
			
			pro5 = dao.findById("OADO");
			pro6 = dao.findById("OAUSER");
			pro7 = dao.findById("OAPASS");
			
			pro8 = dao.findById("TGDO");
			pro9 = dao.findById("TGUSER");
			pro10 = dao.findById("TGPASS");
			
			pro11 = dao.findById("DBDO");
			pro12 = dao.findById("DBUSER");
			pro13 = dao.findById("DBPASS");
			pro14 = dao.findById("STARTCONFIG");
			//session.save(item);
			tran.commit();
			
		 	if (pro1==null){
		 		text.setText(""+java.util.UUID.randomUUID());//			ID
		 	}else{
		 		text.setText(pro1.getV().toString().trim());
		 	}
		 	if (pro2==null){
		 		text_1.setText(""+java.util.UUID.randomUUID());//			USER
		 	}else{
		 		text_1.setText(pro2.getV().toString().trim());
		 	}
		 	if (pro3==null){
		 		text_2.setText("");//			单位
		 	}else{
		 		text_2.setText(pro3.getV().toString().trim());
		 	}
		 	if (pro4==null){
		 		text_3.setText("");//			部门
		 	}else{
		 		text_3.setText(pro4.getV().toString().trim());
		 	}
		 	
		 	
		 	/********************************************************
		 	 * 公文段
		 	 *********************************************************/
		 	if (pro5==null){
		 		btnCheckButton_1.setSelection(false);// 	启用公文追踪
		 	}else{
		 		if (pro5.getV().trim().toUpperCase().equals("TRUE")){
		 			btnCheckButton_1.setSelection(true);// 启用公文追踪
		 		}else if (pro5.getV().trim().toUpperCase().equals("FALSE")){
		 			btnCheckButton_1.setSelection(false);// 启用公文追踪
		 		}else{
		 			btnCheckButton_1.setSelection(true);// 启用公文追踪
		 		}		 	}
		 	if (pro6==null){
		 		text_4.setText("");//			公文追踪用户
		 	}else{
		 		text_4.setText(pro6.getV().toString().trim());
		 	}
		 	if (pro7==null){
		 		text_5.setText("");//			公文追踪密码
		 	}else{
		 		text_5.setText(pro7.getV().toString().trim());
		 	}
		 	
		 	/********************************************************
		 	 * 电报段
		 	 *********************************************************/
		 	if (pro8==null){
		 		btnCheckButton.setSelection(false);// 电报追踪
		 	}else{
		 		if (pro8.getV().trim().toUpperCase().equals("TRUE")){
		 			btnCheckButton.setSelection(true);//电报追踪
		 		}else if (pro8.getV().trim().toUpperCase().equals("FALSE")){
		 			btnCheckButton.setSelection(false);// 电报追踪
		 		}else{
		 			btnCheckButton.setSelection(true);//电报追踪
		 		}
		 	}
		 	if (pro9==null){
		 		text_6.setText("");//			电报追踪用户
		 	}else{
		 		text_6.setText(pro9.getV().toString().trim());
		 	}
		 	if (pro10==null){
		 		text_7.setText("");//			电报追踪密码
		 	}else{
		 		text_7.setText(pro10.getV().toString().trim());
		 	}
		 	
		 	/********************************************************
		 	 * 待办段
		 	 *********************************************************/
		 	if (pro11==null){
		 		btnCheckButton_2.setSelection(false);// 待办
		 	}else{
		 		if (pro11.getV().trim().toUpperCase().equals("TRUE")){
		 			btnCheckButton_2.setSelection(true);// 待办
		 		}else if (pro11.getV().trim().toUpperCase().equals("FALSE")){
		 			btnCheckButton_2.setSelection(false);//待办
		 		}else{
		 			btnCheckButton_2.setSelection(true);// 待办
		 		}
		 	}
		 	if (pro12==null){
		 		text_8.setText("");//			待办用户
		 	}else{
		 		text_8.setText(pro12.getV().toString().trim());
		 	}
		 	if (pro13==null){
		 		text_9.setText("");//			待办密码
		 	}else{
		 		text_9.setText(pro13.getV().toString().trim());
		 	}

		 	if (pro14==null){
		 		btnCheckButton_3.setSelection(false);// 待办
		 	}else{
		 		if (pro14.getV().trim().toUpperCase().equals("TRUE")){
		 			btnCheckButton_3.setSelection(true);// 待办
		 		}else if (pro11.getV().trim().toUpperCase().equals("FALSE")){
		 			btnCheckButton_3.setSelection(false);//待办
		 		}else{
		 			btnCheckButton_3.setSelection(true);// 待办
		 		}
		 	}
		 	//Thread.sleep(50);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	void exit() {
		this.getParent().dispose();
	}

	public static void main(String[] args) {
		new DiaConfig(new Shell(), SWT.ICON_INFORMATION).open();
		
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
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public DiaConfig(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");

	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		init();//20140211
		setScreenPoint(shell);
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(455, 395);
		shell.setText("配置与选项");
		shell.setLayout(new GridLayout(4, false));

		Label lblNewLabel_9 = new Label(shell, SWT.NONE);
		lblNewLabel_9.setAlignment(SWT.CENTER);
		GridData gd_lblNewLabel_9 = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 4, 1);
		gd_lblNewLabel_9.heightHint = 30;
		lblNewLabel_9.setLayoutData(gd_lblNewLabel_9);
		lblNewLabel_9.setText("提示");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("用户ID");

		text = new Text(shell, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));

		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText("用户名");

		text_1 = new Text(shell, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3,
				1));

		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_2.setText("单位");

		text_2 = new Text(shell, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_3.setText("部门");

		text_3 = new Text(shell, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(shell, SWT.NONE);

		btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setText("启用电报追踪");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_4.setText("电报用户");
		
				text_6 = new Text(shell, SWT.BORDER);
				text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
						1));

		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_5.setText("电报密码");
		
				text_7 = new Text(shell, SWT.BORDER);
				text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
						1));
		new Label(shell, SWT.NONE);

		btnCheckButton_1 = new Button(shell, SWT.CHECK);
		btnCheckButton_1.setText("启用公文收文追踪");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
				Label label = new Label(shell, SWT.NONE);
				label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
						1, 1));
				label.setText("公文用户");
		
				text_4 = new Text(shell, SWT.BORDER);
				text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
						1));
		
				Label lblNewLabel_6 = new Label(shell, SWT.NONE);
				lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
						false, 1, 1));
				lblNewLabel_6.setText("公文密码");
		
				text_5 = new Text(shell, SWT.BORDER);
				text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
						1));
				new Label(shell, SWT.NONE);
		
				btnCheckButton_2 = new Button(shell, SWT.CHECK);
				btnCheckButton_2.setText("启用公文待办追踪");
				new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
				Label lblNewLabel_7 = new Label(shell, SWT.NONE);
				lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
						false, 1, 1));
				lblNewLabel_7.setText("待办用户");
		
				text_8 = new Text(shell, SWT.BORDER);
				text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
						1));
		
				Label lblNewLabel_8 = new Label(shell, SWT.NONE);
				lblNewLabel_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
						false, 1, 1));
				lblNewLabel_8.setText("待办密码");
		
				text_9 = new Text(shell, SWT.BORDER);
				text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
						1));
				new Label(shell, SWT.NONE);
				
				btnCheckButton_3 = new Button(shell, SWT.CHECK);
				btnCheckButton_3.setText("启动系统时显示");
				new Label(shell, SWT.NONE);
				new Label(shell, SWT.NONE);
		
				Composite composite = new Composite(shell, SWT.NONE);
				composite.setLayout(null);
				GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false,
						false, 4, 1);
				gd_composite.heightHint = 66;
				composite.setLayoutData(gd_composite);
				
						Button btnNewButton = new Button(composite, SWT.NONE);
						btnNewButton.setBounds(68, 27, 90, 27);
						btnNewButton.addSelectionListener(new SelectionAdapter() {
							@Override
							public void widgetSelected(SelectionEvent e) {
								save();
							}
						});
						btnNewButton.setText("保存");
						
								Button btnNewButton_1 = new Button(composite, SWT.NONE);
								btnNewButton_1.setBounds(256, 27, 90, 27);
								btnNewButton_1.addSelectionListener(new SelectionAdapter() {
									@Override
									public void widgetSelected(SelectionEvent e) {
										exit();
									}
								});
								btnNewButton_1.setText("退出");
	}
}
