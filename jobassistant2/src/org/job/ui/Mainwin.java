package org.job.ui;

import java.util.List;

import jodd.datetime.JDateTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.hibernate.Session;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;
import org.job.interf.INotifyMessage;
import org.job.util.SWTResourceManager;

import com.job.HibernateUtil;

public class Mainwin implements INotifyMessage {
	protected Shell shell= null;
	private Text text= null;
	private Table table= null;
	private Text text_1= null;
	private Session session= null;
	private Button btnNewButton_2 = null;
	private Button btnNewButton_3 = null;
	private Button btnNewButton_4 = null;
	private Button btnNewButton_5 = null;
	private Button btnNewButton_6 = null;

	class Tip implements Runnable{
		private String info = null;
		public Tip(String info) {
			super();
			this.info = info;
		}
		@Override
		public void run() {
			String inf[] = info.split("\\|");
			System.out.println(info + "--" + inf.length);
			if (btnNewButton_2!=null){ 
				btnNewButton_2.setText(inf[2].substring(4,8)+"-"+inf[2].substring(9));
				btnNewButton_2.setData(inf[2].substring(0,8));
			}
			if (btnNewButton_3!=null){
				btnNewButton_3.setText(inf[3].substring(4,8)+"-"+inf[3].substring(9));
				btnNewButton_3.setData(inf[3].substring(0,8));
			}
			if (btnNewButton_4!=null){
				btnNewButton_4.setText(inf[4].substring(4,8)+"-"+inf[4].substring(9));
				btnNewButton_4.setData(inf[4].substring(0,8));
			}
			if (btnNewButton_5!=null){
				btnNewButton_5.setText(inf[5].substring(4,8)+"-"+inf[5].substring(9));
				btnNewButton_5.setData(inf[5].substring(0,8));
			}
			if (btnNewButton_6!=null){
				btnNewButton_6.setText(inf[6].substring(4,8)+"-"+inf[6].substring(9));
				btnNewButton_6.setData(inf[6].substring(0,8));
			}
		}
		
	}
	
	@Override
	public void notifyMessage(String info) {
		if (info.startsWith("NEWFILE")){
			String inf[] =info.split("\\|");
			System.out.println(info+"--"+inf.length);
		}else if (info.startsWith("TDOAYCOUNT")){
			Display.getDefault().syncExec(new Tip(info)); //SWT 多线程 
		}else{
			
		}
		
	}

	void init() {
		init_catalog();
		init_datetextbox();
	}

	// 初始值
	private void init_datetextbox() {
		if (text_1 != null)
			text_1.setText(new JDateTime(System.currentTimeMillis())
					.toString("YYYYMMDD"));
	}

	private void init_catalog() {
		// 表格
		// String[] titles = { "编号", "类型", "发文日期", "发送单位","标题","电报/文件号","入库日期"
		// };
		String[] titles = { "类型", "标题", "电报/文件号" };
		for (int i = table.getColumnCount(); i > 0; i--) {
			table.getColumn(i - 1).dispose();
		}
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
			// if (loopIndex ==0) column.setWidth(50);
			// else
			if (loopIndex == 0)
				column.setWidth(50);
			// else if (loopIndex ==2) column.setWidth(180);
			// else if (loopIndex ==3) column.setWidth(120);
			else if (loopIndex == 1)
				column.setWidth(400);
			else if (loopIndex == 2)
				column.setWidth(180);
			// else if (loopIndex ==6) column.setWidth(100);
			else
				column.setWidth(120);
		}
	}

	void settabledata(Doccatalog cata, int i) {
		TableItem item = new TableItem(table, SWT.NULL);
		item.setData(cata);
		// item.setText("Item " + i);//??
		// item.setText(0, ""+ cata.getDocid());
		String temp = "";
		if (cata.getType().trim().equals("TG")) {
			temp = "电报";
		} else if (cata.getType().trim().equals("OA")) {
			temp = "公文";
		} else {
			temp = "文件";
		}
		item.setText(0, "" + temp);
		// item.setText(2, ""+ cata.getDocsendtime());
		// item.setText(3, ""+ cata.getDocsender());
		item.setText(1, "" + cata.getDoccaption());
		item.setText(2, "" + cata.getDoccode());
		// item.setText(6, ""+ cata.getIndate());
	}

	void query() {
		try {
			for (int i = table.getItemCount(); i > 0; i--)
				table.getItems()[i - 1].dispose();
			session = HibernateUtil.currentSession();// HibernateSessionFactory.getSession();
			session.beginTransaction();
			DoccatalogDAO catadao = new DoccatalogDAO();
			@SuppressWarnings("unchecked")
			List<Doccatalog> list = catadao.findByDocsenddate(new Integer(
					text_1.getText().trim()));
			for (int i = 0; i < list.size(); i++) {
				Doccatalog cata = (Doccatalog) list.get(i);
				// if (!cata.getIshidden()){
				settabledata(cata, i);
				// }
			}
			list.clear();
			session.getTransaction().commit();
			Thread.sleep(50);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void today() {
		text_1.setText(new JDateTime(System.currentTimeMillis()).toString("YYYYMMDD"));
	}

	private void add1day() {
		JDateTime jdt = new JDateTime(text_1.getText(), "YYYYMMDD");
		jdt.addDay(1);
		text_1.setText(jdt.toString("YYYYMMDD"));
	}

	private void subtract1day() {
		JDateTime jdt = new JDateTime(text_1.getText(), "YYYYMMDD");
		jdt.subDay(1);
		text_1.setText(jdt.toString("YYYYMMDD"));
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Mainwin window = new Mainwin();
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
		shell.setSize(400, 800);
		shell.setText("电报公文提醒系统");
		shell.setLayout(new GridLayout(1, false));

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("系统");

		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("设置");

		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("帮助");

		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		composite.setLayout(new GridLayout(6, false));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_composite.widthHint = 369;
		gd_composite.heightHint = 137;
		composite.setLayoutData(gd_composite);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("请选择查看日期");

		text_1 = new Text(composite, SWT.BORDER);
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				query();
			}
		});
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				subtract1day();
			}
		});
		btnNewButton.setText("向前");

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				add1day();
			}
		});
		btnNewButton_1.setText("向后");

		Button btnNewButton_8 = new Button(composite, SWT.NONE);
		btnNewButton_8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				today();
			}
		});
		btnNewButton_8.setText("今日");

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setFont(SWTResourceManager
				.getFont("微软雅黑", 14, SWT.NORMAL));
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText("提示信息");

		Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 6, 1);
		gd_composite_1.heightHint = 40;
		composite_1.setLayoutData(gd_composite_1);

		btnNewButton_2 = new Button(composite_1, SWT.NONE);
		btnNewButton_3 = new Button(composite_1, SWT.NONE);
		btnNewButton_4 = new Button(composite_1, SWT.NONE);
		btnNewButton_5 = new Button(composite_1, SWT.NONE);
		btnNewButton_6 = new Button(composite_1, SWT.NONE);

		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNewButton_2.getData()!=null)text_1.setText(btnNewButton_2.getData().toString());
			}
		});
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNewButton_3.getData()!=null)text_1.setText(btnNewButton_3.getData().toString());
			}
		});
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNewButton_4.getData()!=null)text_1.setText(btnNewButton_4.getData().toString());
			}
		});
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNewButton_5.getData()!=null)text_1.setText(btnNewButton_5.getData().toString());
			}
		});
		btnNewButton_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNewButton_6.getData()!=null)text_1.setText(btnNewButton_6.getData().toString());
			}
		});
		
		Composite composite_2 = new Composite(composite, SWT.NONE);
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 6, 1);
		gd_composite_2.heightHint = 51;
		composite_2.setLayoutData(gd_composite_2);

		Button btnCheckButton = new Button(composite_2, SWT.CHECK);
		btnCheckButton.setBounds(10, 10, 53, 17);
		btnCheckButton.setText("电报");

		Button btnCheckButton_1 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_1.setBounds(69, 10, 53, 17);
		btnCheckButton_1.setText("公文");

		Button btnCheckButton_2 = new Button(composite_2, SWT.CHECK);
		btnCheckButton_2.setBounds(156, 10, 53, 17);
		btnCheckButton_2.setText("过滤");

		Button btnNewButton_7 = new Button(composite_2, SWT.NONE);
		btnNewButton_7.setBounds(215, 5, 80, 27);
		btnNewButton_7.setText("设置过滤");

		ScrolledComposite scrolledComposite = new ScrolledComposite(shell,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.LEFT, SWT.FILL, false,
				true, 1, 1);
		gd_scrolledComposite.widthHint = 353;
		scrolledComposite.setLayoutData(gd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table
				.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		text = new Text(shell, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		init();
	}

}
