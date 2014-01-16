package railway.bj.admin.my.job.ui;

import java.sql.Timestamp;
import java.util.List;

import jodd.datetime.JDateTime;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
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
import org.eclipse.wb.swt.SWTResourceManager;
import org.hibernate.Session;

import railway.bj.admin.my.job.dao.HibernateSessionFactory;
import railway.bj.admin.my.job.dao.entity.Doccatalog;
import railway.bj.admin.my.job.dao.entity.DoccatalogDAO;
import railway.bj.admin.my.job.dao.entity.Docrecv;
import railway.bj.admin.my.job.dao.entity.DocrecvDAO;
import railway.bj.admin.my.job.dao.entity.Vdocrecv;
import railway.bj.admin.my.job.dao.entity.VdocrecvDAO;
import railway.bj.admin.my.job.dao.entity.VdocrecvId;

public class RecvDocMainwin {
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Session session;
	private RecvDocInput recvDocInput;
	private Button btnCheckButton;
	private Table table;
	private TableViewer tableViewer;// = new TableViewer(shell, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
	private Table table_1;
	private TableViewer tableViewer_1;// = new TableViewer(shell, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
	private Composite composite_6;
	
//	public class TableViewerLabelProvider extends LabelProvider implements ITableLabelProvider{
//
//	}
	/**
	 * 
	 */
	void doisoktag(){
		try{
			session.beginTransaction();
			if (table_1.getSelectionCount()>0){
				if (table_1.getSelection()[0].getData() instanceof VdocrecvId){
					VdocrecvId vrecvid =(VdocrecvId)table_1.getSelection()[0].getData();
					DocrecvDAO docrecvdao = new DocrecvDAO();
					Docrecv docrecv = docrecvdao.findById(vrecvid.getDocid());
					docrecv.setIsok(!vrecvid.getIsok());
					docrecv.setOktimestamp(new Timestamp(System.currentTimeMillis()));
					String memo = " ";//完成情况
					InputDialog input = new InputDialog(shell,"输入框", "请输入完成情况:"," ",null);
					if(input.open()== Window.OK){
						memo = input.getValue().toString();
					}else if(input.open()==Window.CANCEL){
						return;
					}else{
						return;
					}
					docrecv.setOkmemo(memo);
					docrecvdao.merge(docrecv);
					new AutoCloseDialog(shell, AutoCloseDialog.INFORMATION, docrecv.getIsok()?"设置为完成":"设置为未完", null , 2000l).open();
				}
			}
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			new AutoCloseDialog(shell, AutoCloseDialog.ERROR,"出现错误!", null , 2000l).open();
		}
		queryrecvdoc();
	}
	
	/**
	 * 
	 */
	void queryrecvdoc(){
		try{
			for (int i =table_1.getItemCount();i>0;i--) table_1.getItems()[i-1].dispose();
			session.beginTransaction();
			VdocrecvDAO vrecvdao = new VdocrecvDAO();
			List<Vdocrecv> list = vrecvdao.findAll();
			for (int i = 0; i < list.size(); i++) {
			Vdocrecv vrecv = (Vdocrecv)list.get(i);
				//if (!vrecv.getIsok())
				setvRecvtabledata(vrecv.getId());
			}
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	void setvRecvtabledata(VdocrecvId vrecv){
		if (vrecv.getIsok() && !btnCheckButton.getSelection()) return;
		TableItem item = new TableItem(table_1, SWT.NULL);
		item.setData(vrecv);
		item.setText(0, vrecv.getIsok()?"已完":"未完");
		String temp ="";
		if (vrecv.getType().equals("TG"))temp="电报"; 
		item.setText(1, ""+temp);
		item.setText(2, ""+ vrecv.getDoccode());
		item.setText(3, ""+ vrecv.getTriggertime());
		item.setText(4, ""+ vrecv.getTransmitter());
		item.setText(5, ""+ vrecv.getDoccaption());
		item.setText(6, ""+ vrecv.getMemo());
		item.setText(7, ""+ vrecv.getDocsendtime());
		item.setText(8, ""+ vrecv.getRecvdate());
		item.setText(9, ""+ vrecv.getRecvTag());
		item.setText(10, ""+ vrecv.getDocsender());
		item.setText(11, ""+ vrecv.getOktimestamp());
		item.setText(12, ""+ vrecv.getOkmemo());
	}
	
	/**
	 * 签收表
	 */
	void initrecvtable(){
		TableLayout layout_1 = new TableLayout();

		/*
		 * NOTE: MeasureItem, PaintItem and EraseItem are called repeatedly.
		 * Therefore, it is critical for performance that these methods be
		 * as efficient as possible.
		 */
		final int TEXT_MARGIN = 5;
		table_1.addListener(SWT.MeasureItem, new Listener() {
			@Override
			public void handleEvent(Event event) {
				TableItem item = (TableItem)event.item;
				String text = item.getText(event.index);
				Point size = event.gc.textExtent(text);
				event.width = size.x + 2 * TEXT_MARGIN;
				event.height = Math.max(event.height, size.y + TEXT_MARGIN);
			}
		});
		table_1.addListener(SWT.EraseItem, new Listener() {
			@Override
			public void handleEvent(Event event) {
				event.detail &= ~SWT.FOREGROUND;
			}
		});
		table_1.addListener(SWT.PaintItem, new Listener() {
			@Override
			public void handleEvent(Event event) {
				TableItem item = (TableItem)event.item;
				String text = item.getText(event.index);
				/* center column 1 vertically */
				int yOffset = 0;
				if (event.index == 1) {
					Point size = event.gc.textExtent(text);
					yOffset = Math.max(0, (event.height - size.y) / 2);
				}
				event.gc.drawText(text, event.x + TEXT_MARGIN, event.y + yOffset, true);
			}
		});
		
		//表格
		String[] titles = {"办完","类型","电报/文件号","执行时间","传达人","标题","注意事项","发文时间","传达日","标签","发送单位","完成时间","完成情况"};
		for (int i =table_1.getColumnCount();i>0;i--){
			table_1.getColumn(i-1).dispose();
		}
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table_1, SWT.NULL);
			column.setText(titles[loopIndex]);
			if (loopIndex ==0) column.setWidth(50);
			else if (loopIndex ==1) column.setWidth(50);
			else if (loopIndex ==2) column.setWidth(180);
			else if (loopIndex ==3) column.setWidth(120);
			else if (loopIndex ==4) column.setWidth(120);
			else if (loopIndex ==5) column.setWidth(400);
			else if (loopIndex ==6) column.setWidth(100);
			else if (loopIndex ==7) column.setWidth(100);
			else if (loopIndex ==8) column.setWidth(100);
			else if (loopIndex ==9) column.setWidth(100);
			else if (loopIndex ==10) column.setWidth(100);
			else if (loopIndex ==11) column.setWidth(100);
			else if (loopIndex ==12) column.setWidth(100);
			else column.setWidth(120);
		}
		//table_1.setLayout(layout_1);
	}
	/**
	 * 选择一个文件，传入一个保存项；
	 */
	void selectcatalog(){
		if (table.getSelection()!=null){
			if (table.getSelection()[0].getData() instanceof Doccatalog){
				recvDocInput.setDoccatalog((Doccatalog)table.getSelection()[0].getData(),session);
			}
		}
	}
	
	//初始值
	void initcatalog(){
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
		item.setData(cata);
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
			List<Doccatalog> list = catadao.findByDocsenddate(new Integer(text_2.getText().trim()));
			for (int i = 0; i < list.size(); i++) {
				Doccatalog cata = (Doccatalog)list.get(i);
				//if (!cata.getIshidden()){
					settabledata(cata,i);
				//}
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
		initcatalog();
		initrecvtable();
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
		shell.setSize(new Point(1100, 578));
		shell.setSize(1091, 601);
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
		
		Button btnNewButton_4 = new Button(coolBar, SWT.NONE);
		coolItem.setControl(btnNewButton_4);
		btnNewButton_4.setText("说明");

//		SashForm sashForm = new SashForm(shell, SWT.NONE);
//		sashForm.setSashWidth(2);
//		sashForm.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1,1));

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
										
																		TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
																		tbtmNewItem_2.setText("待收文件");
																		composite_6 = new Composite(tabFolder, SWT.NONE);
																		tbtmNewItem_2.setControl(composite_6);
																		composite_6.setLayout(new GridLayout(2, false));
																		
//																		Composite composite = new Composite(sashForm, SWT.NONE);
//																		composite.setLayout(new GridLayout(1, false));
																		
																		Group group = new Group(composite_6, SWT.NONE);
																		group.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
																		group.setBounds(0, 0, 70, 87);
																		group.setLayout(new GridLayout(6, false));
																		
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
																		
																		Composite composite_3 = new Composite(group, SWT.NONE);
																		composite_3.setLayout(new RowLayout(SWT.HORIZONTAL));
																		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 3);
																		gd_composite_3.widthHint = 243;
																		composite_3.setLayoutData(gd_composite_3);
																		
																		Button btnRadioButton_2 = new Button(group, SWT.RADIO);
																		btnRadioButton_2.setText("关键字");
																		
																		text_1 = new Text(group, SWT.BORDER);
																		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
																		
																		Button btnNewButton = new Button(group, SWT.NONE);
																		GridData gd_btnNewButton = new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1);
																		gd_btnNewButton.widthHint = 86;
																		btnNewButton.setLayoutData(gd_btnNewButton);
																		btnNewButton.setText("查询");
																		
																		Button btnRadioButton = new Button(group, SWT.RADIO);
																		btnRadioButton.setText("三日内未签收");
																		new Label(group, SWT.NONE);
																		new Label(group, SWT.NONE);
																		new Label(group, SWT.NONE);
																						
																						recvDocInput = new RecvDocInput(composite_6, SWT.NONE);
																						recvDocInput.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 2));
																						GridLayout gridLayout = (GridLayout) recvDocInput.getLayout();
																						gridLayout.marginTop = 120;
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																						new Label(recvDocInput, SWT.NONE);
																				
																						ScrolledComposite scrolledComposite = new ScrolledComposite(composite_6,
																								SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
																						scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
																						scrolledComposite.setSize(303, 411);
																						scrolledComposite.setExpandHorizontal(true);
																						scrolledComposite.setExpandVertical(true);
																						//TODO
																								tableViewer = new TableViewer(scrolledComposite, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
																								table = tableViewer.getTable();// new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
																								table.addMouseListener(new MouseAdapter() {
																									@Override
																									public void mouseDoubleClick(MouseEvent e) {
																										selectcatalog();
																									}
																								});
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
										
										TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
										tbtmNewItem_1.setText("已签收");
										
										Composite composite_1 = new Composite(tabFolder, SWT.NONE);
										tbtmNewItem_1.setControl(composite_1);
										composite_1.setLayout(new GridLayout(2, false));
										
										Composite composite_4 = new Composite(composite_1, SWT.NONE);
										composite_4.setLayout(new GridLayout(4, false));
										GridData gd_composite_4 = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
										gd_composite_4.heightHint = 95;
										composite_4.setLayoutData(gd_composite_4);
										new Label(composite_4, SWT.NONE);
										new Label(composite_4, SWT.NONE);
										
										btnCheckButton = new Button(composite_4, SWT.CHECK);
										btnCheckButton.setText("显示已完成");
										
										Button btnNewButton_3 = new Button(composite_4, SWT.NONE);
										btnNewButton_3.addSelectionListener(new SelectionAdapter() {
											@Override
											public void widgetSelected(SelectionEvent e) {
												queryrecvdoc();
											}
										});
										GridData gd_btnNewButton_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
										gd_btnNewButton_3.widthHint = 120;
										btnNewButton_3.setLayoutData(gd_btnNewButton_3);
										btnNewButton_3.setText("查询");
										
										Composite composite_5 = new Composite(composite_1, SWT.NONE);
										composite_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
										GridData gd_composite_5 = new GridData(SWT.RIGHT, SWT.FILL, false, false, 1, 1);
										gd_composite_5.widthHint = 389;
										composite_5.setLayoutData(gd_composite_5);
										
										ScrolledComposite scrolledComposite_1 = new ScrolledComposite(composite_1, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
										GridData gd_scrolledComposite_1 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
										gd_scrolledComposite_1.widthHint = 732;
										scrolledComposite_1.setLayoutData(gd_scrolledComposite_1);
										scrolledComposite_1.setBounds(0, 0, 89, 89);
										scrolledComposite_1.setExpandHorizontal(true);
										scrolledComposite_1.setExpandVertical(true);
										tableViewer_1 = new TableViewer(scrolledComposite_1, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER|SWT.V_SCROLL|SWT.H_SCROLL);
										table_1 = tableViewer_1.getTable();//new Table(scrolledComposite_1, SWT.BORDER | SWT.FULL_SELECTION);
										table_1.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseDoubleClick(MouseEvent e) {
												doisoktag();
											}
										});
										table_1.setBounds(0, 0, 89, 51);
										table_1.setHeaderVisible(true);
										table_1.setLinesVisible(true);
										scrolledComposite_1.setContent(table_1);
										scrolledComposite_1.setMinSize(table_1.computeSize(SWT.DEFAULT, SWT.DEFAULT));
										new Label(composite_1, SWT.NONE);
										
										
		//sashForm.setWeights(new int[] {100});

		text = new Text(shell, SWT.BORDER);
		text.setEnabled(false);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));

	}
}
