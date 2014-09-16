package my.outline.gui;

import java.util.Hashtable;
import java.util.List;

import my.outline.dao.entity.Tag;
import my.outline.dao.entity.TagDAO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DInputTag extends Dialog {
	private static final Logger log = LoggerFactory.getLogger(DInputTag.class);
	Session session = null;
	Hashtable<String,Integer> htTags = new Hashtable<String,Integer>();
	Hashtable<Integer,String> htTags2 = new Hashtable<Integer,String>();
	protected Object result;
	protected Shell shlInputtag;
	private Combo combo;
	private Text text;
	private Text text_1;
	private Text text_2;

	void status(String info){
		text_2.setText(info);
	}
	
	void loadTag(String name){
		Transaction t = null;
		try{
			t = session.beginTransaction();
			TagDAO dao = new TagDAO();
			List<Tag> tags = dao.findByName(name);
			for (Tag tag : tags) {
				text.setText(tag.getTagid()+"");
				if (tag.getPtagid()==null || tag.getPtagid()<=0){
					combo.setText("");
				}else{
					combo.setText(htTags2.get(tag.getPtagid()));
				}
				text_1.setText(tag.getName());
			}
			t.commit();
			status("加载成功。");
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
			log.error("loadTag()");
			status("加载失败!");
		}
	}
	
	void save(){
		if (text_1.getText().length()<=0){
			status("保存失败2!");
			return;
		}
		Transaction t = null;
		try{
			t = session.beginTransaction();
			TagDAO dao = new TagDAO();
			Tag tag = new Tag();
			if (combo.getSelectionIndex()>=0){
				tag.setPtagid(htTags.get(combo.getText()));
			}else{
				tag.setPtagid(0);
			}
			tag.setName(text_1.getText());
			dao.save(tag);
			t.commit();
			status("保存成功。");
			loadTags();
			loadTag(text_1.getText());
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
			log.error("loadTags()");
			status("保存失败!");
			loadTags();
		}
	}
	
	@SuppressWarnings({"unchecked" })
	void loadTags(){
		Transaction t = null;
		try{
			combo.setText("");
			combo.removeAll();
			htTags.clear();
			htTags2.clear();
			t = session.beginTransaction();
			TagDAO dao = new TagDAO();
			List<Tag> tags = dao.findAll();
			for (Tag tag : tags) {
				combo.add(tag.getName());
				htTags.put(tag.getName(), tag.getTagid());
				htTags2.put(tag.getTagid(),tag.getName());
			}
			t.commit();
		}catch(Exception ex){
			t.rollback();
			ex.printStackTrace();
			log.error("loadTags()");
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
    
	private void exit(){
		shlInputtag.close();
	}
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DInputTag(Shell parent, int style,Session session) {
		super(parent, style);
		setText("SWT Dialog");
		this.session = session; 
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		setScreenPoint(shlInputtag);
		loadTags();
		shlInputtag.open();
		shlInputtag.layout();
		Display display = getParent().getDisplay();
		while (!shlInputtag.isDisposed()) {
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
		shlInputtag = new Shell(getParent(), getStyle());
		shlInputtag.setSize(450, 175);
		shlInputtag.setText("InputTag");
		shlInputtag.setLayout(new GridLayout(2, false));
		
		ToolBar toolBar = new ToolBar(shlInputtag, SWT.FLAT | SWT.RIGHT);
		GridData gd_toolBar = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_toolBar.widthHint = 381;
		toolBar.setLayoutData(gd_toolBar);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				save();
			}
		});
		tltmNewItem.setText("保存");
		
		ToolItem tltmNewItem_2 = new ToolItem(toolBar, SWT.SEPARATOR);
		tltmNewItem_2.setText("New Item");
		
		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exit();
			}
		});
		tltmNewItem_1.setText("退出");
		
		Label lblNewLabel = new Label(shlInputtag, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("标识编号");
		
		text = new Text(shlInputtag, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(shlInputtag, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("父标识");
		
		combo = new Combo(shlInputtag, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_2 = new Label(shlInputtag, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("标识名");
		
		text_1 = new Text(shlInputtag, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		text_2 = new Text(shlInputtag, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

	}
}
