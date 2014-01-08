package railway.bj.admin.my.job.ui;

import java.util.List;

import jodd.datetime.JDateTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.hibernate.Session;

import railway.bj.admin.my.job.dao.HibernateSessionFactory;
import railway.bj.admin.my.job.dao.entity.Doccatalog;
import railway.bj.admin.my.job.dao.entity.DoccatalogDAO;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class RecvDocMainwin {
	Session session;
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Table table;
	
	//初始值
	void init(){
		//表格
		String[] titles = { "编号", "类型", "发文日期", "发送单位","标题","电报/文件号","入库日期" };
		for (int i =table.getColumnCount();i>0;i--){
			table.getColumn(i-1).dispose();
		}
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
			if (loopIndex ==0) column.setWidth(50);
			else if (loopIndex ==1) column.setWidth(50);
			else if (loopIndex ==2) column.setWidth(180);
			else if (loopIndex ==3) column.setWidth(120);
			else if (loopIndex ==4) column.setWidth(400);
			else if (loopIndex ==5) column.setWidth(180);
			else if (loopIndex ==6) column.setWidth(100);
			else column.setWidth(120);
		}
	    table.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		        if (event.detail == SWT.CHECK) {
		          text.setText("You checked " + event.item);
		        } else {
		          text.setText("You selected " + event.item);
		        }
		      }
		    });
	    //默认数据
	    if (text_2!=null) text_2.setText(new JDateTime(System.currentTimeMillis()).toString("YYYYMMDD"));
	}
	
	void add1day(){
		JDateTime jdt = new JDateTime(text_2.getText(),"YYYYMMDD");
		jdt.addDay(1);
		text_2.setText(jdt.toString("YYYYMMDD"));
	}
	
	void subtract1day(){
		JDateTime jdt = new JDateTime(text_2.getText(),"YYYYMMDD");
		jdt.subDay(1);
		text_2.setText(jdt.toString("YYYYMMDD"));
	}
	
	
	void settabledata(Doccatalog cata,int i){
		TableItem item = new TableItem(table, SWT.NULL);
		item.setText("Item " + i);//??
		item.setText(0, ""+ cata.getDocid());
		String temp ="";
		if (cata.getType().equals("TG"))temp="电报"; 
		item.setText(1, ""+temp);
		item.setText(2, ""+ cata.getDocsendtime());
		item.setText(3, ""+ cata.getDocsender());
		item.setText(4, ""+ cata.getDoccaption());
		item.setText(5, ""+ cata.getDoccode());
		item.setText(6, ""+ cata.getIndate());
	}
	
	void inidb(){
		session= HibernateSessionFactory.getSession();
	}
	
	void test(){
		try{
			for (int i =table.getItemCount();i>0;i--) table.getItems()[i-1].dispose();
			session.beginTransaction();
			DoccatalogDAO catadao = new DoccatalogDAO();
			@SuppressWarnings("unchecked")
			List<Doccatalog> list= catadao.findByDocsenddate(new Integer(text_2.getText().trim()));
			for (int i = 0; i < list.size(); i++) {
				Doccatalog cata = (Doccatalog)list.get(i);
				if (!cata.getIshidden()){
					settabledata(cata,i);
				}
			}
			list.clear();
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RecvDocMainwin window = new RecvDocMainwin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		inidb();
		init();
		//test();//20140108
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(980, 601);
		shell.setText("工作平台");
		shell.setLayout(new GridLayout(1, false));

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("文件");

		Menu menu_1 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_1);

		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.setText("New Item");

		CoolBar coolBar = new CoolBar(shell, SWT.FLAT);

		CoolItem coolItem = new CoolItem(coolBar, SWT.NONE);

//		SashForm sashForm = new SashForm(shell, SWT.NONE);
//		sashForm.setSashWidth(2);
//		sashForm.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1,1));

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
										
										TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
										tbtmNewItem.setText("文件签收");
										
										SashForm sashForm = new SashForm(tabFolder, SWT.NONE);
										tbtmNewItem.setControl(sashForm);
																		
																		Composite composite = new Composite(sashForm, SWT.NONE);
																		composite.setLayout(new GridLayout(1, false));
																		
																		Group group = new Group(composite, SWT.NONE);
																		group.setBounds(0, 0, 70, 87);
																		group.setLayout(new GridLayout(5, false));
																		
																		Button btnRadioButton_1 = new Button(group, SWT.RADIO);
																		btnRadioButton_1.setText("按日期查询");
																		
																		text_2 = new Text(group, SWT.BORDER);
																		text_2.addModifyListener(new ModifyListener() {
																			public void modifyText(ModifyEvent arg0) {
																				test();
																			}
																		});
																		GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
																		gd_text_2.widthHint = 100;
																		text_2.setLayoutData(gd_text_2);
																		
																		Button btnNewButton_1 = new Button(group, SWT.NONE);
																		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
																			@Override
																			public void widgetSelected(SelectionEvent e) {
																				subtract1day();
																			}
																		});
																		btnNewButton_1.setText("向前");
																		
																		Button btnNewButton_2 = new Button(group, SWT.NONE);
																		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
																			@Override
																			public void widgetSelected(SelectionEvent e) {
																				add1day();
																			}
																		});
																		btnNewButton_2.setText("向后");
																		
																		Button btnNewButton = new Button(group, SWT.NONE);
																		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 3);
																		gd_btnNewButton.widthHint = 86;
																		btnNewButton.setLayoutData(gd_btnNewButton);
																		btnNewButton.setText("查询");
																		
																		Button btnRadioButton_2 = new Button(group, SWT.RADIO);
																		btnRadioButton_2.setText("关键字");
																		
																		text_1 = new Text(group, SWT.BORDER);
																		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
																		new Label(group, SWT.NONE);
																		new Label(group, SWT.NONE);
																		
																		Button btnRadioButton = new Button(group, SWT.RADIO);
																		btnRadioButton.setText("三日内未签收");
																		new Label(group, SWT.NONE);
																		new Label(group, SWT.NONE);
																		new Label(group, SWT.NONE);
																		
																				ScrolledComposite scrolledComposite = new ScrolledComposite(composite,
																						SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
																				scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
																				scrolledComposite.setSize(303, 411);
																				scrolledComposite.setExpandHorizontal(true);
																				scrolledComposite.setExpandVertical(true);
																				
																						table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
																						table.setHeaderVisible(true);
																						table.setLinesVisible(true);
																						
																								TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
																								tblclmnNewColumn.setWidth(100);
																								tblclmnNewColumn.setText("New Column");
																								
																										TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
																										tblclmnNewColumn_1.setWidth(100);
																										tblclmnNewColumn_1.setText("New Column");
																										scrolledComposite.setContent(table);
																										scrolledComposite.setMinSize(table
																												.computeSize(SWT.DEFAULT, SWT.DEFAULT));
																										
																										Composite composite_2 = new Composite(sashForm, SWT.NONE);
																										composite_2.setBounds(0, 0, 64, 64);
																										composite_2.setLayout(new RowLayout(SWT.HORIZONTAL));
										sashForm.setWeights(new int[] {6, 4});
										
										TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
										tbtmNewItem_1.setText("已签收");
		//sashForm.setWeights(new int[] {100});

		text = new Text(shell, SWT.BORDER);
		text.setEnabled(false);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));

	}
}
