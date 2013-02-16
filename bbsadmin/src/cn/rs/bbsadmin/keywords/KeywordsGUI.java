package cn.rs.bbsadmin.keywords;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class KeywordsGUI extends JDialog {
	private DefaultListModel model = new DefaultListModel(); 
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private KeywordsUtil ku = new KeywordsUtil();
	private JList list = null;
	private JScrollPane scrollPane;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	
	public void loadProp(){
		model.clear();
		Properties props = ku.readProperties();
		Enumeration en = props.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			String property = props.getProperty(key);
			//System.out.println(key +":" + Property);
			model.addElement(property);
		}

	}
	
	public void deleteKeyword(){
		int index = list.getSelectedIndex();
		int ioption = JOptionPane.showConfirmDialog(null, "确认删除"+model.get(index).toString(), "请确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		//System.out.println(ioption+"");
		//0是1取消
		if (ioption==0){//是
			model.remove(index);
			saveProp();
		}
	}
	
	//1.回车
	//2.写入
	//3.重新加载
	//4.清空文本框
	public void enterProp(JTextField text){
		if(text.getText().length()>0){
			model.addElement(text.getText());
			saveProp();
		}
		loadProp();
		text.setText("");
	}
	
	public void saveProp(){
		ku.clearProperties();
		for (int i=0;i<model.getSize();i++){
			ku.writeProperties(""+(i+1), model.getElementAt(i).toString().trim());
		}
	}
	
	private void close(){
		this.dispose();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			KeywordsGUI dialog = new KeywordsGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.loadProp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public KeywordsGUI() {
		setTitle("关键字管理");
		setBounds(100, 100, 400, 460);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				list = new JList();
				list.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount()==2)deleteKeyword();
					}
				});
				list.setModel(model);//做测试！
				list.setFont(new Font("微软雅黑", Font.PLAIN, 16));
				scrollPane.setViewportView(list);
			}
		}
		{
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					//System.out.println(arg0.getKeyCode()+":"+KeyEvent.VK_ENTER);
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
						enterProp(textField);
					}
				}
			});
			textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			contentPanel.add(textField, BorderLayout.NORTH);
			textField.setColumns(10);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确定");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						close();
					}
				});
//				okButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent arg0) {
//						close();
//					}
//				});
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
				cancelButton.setActionCommand("Cancel");
				cancelButton.setVisible(false);
				buttonPane.add(cancelButton);
			}
		}
		this.setLocationRelativeTo(null);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, getContentPane(), contentPanel, scrollPane, list, buttonPane, okButton, cancelButton}));
	}

}
