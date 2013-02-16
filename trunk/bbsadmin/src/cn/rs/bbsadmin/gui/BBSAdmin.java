package cn.rs.bbsadmin.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import cn.rs.bbsadmin.keywords.KeywordsGUI;
import cn.rs.bbsadmin.notifyinterface.IReceiveNotice;
import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;
import cn.rs.bbsadmin.notifyinterface.message.ProgressMessage;

import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import com.bitagentur.chart.JChartLibBaseChart;
import com.bitagentur.chart.JChartLibLinechart;
import com.bitagentur.data.JChartLibDataSet;
import com.bitagentur.data.JChartLibSerie;
import com.bitagentur.renderer.JChartLibPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

public class BBSAdmin implements IReceiveNotice{
	JProgressBar progressBar = null;
	JTextPane textPane = null;
	//JList list = null;
	//DefaultListModel model = new DefaultListModel();
	
	public void test(){
		testchart(); 
	}
	
	@Override
	public boolean notifyMessage(NoticeMessage message) {
		//model.addElement(message);
		//model.add(0, message);
		//list.repaint();
		
		textPane.setText(message +textPane.getText());
		return true;
	}

	@Override
	public boolean notifyProgress(ProgressMessage progress) {
		if (progress.getiTotle()==0){
			textPane.setText("");
			return true;
		}
		progressBar.setMinimum(0);
		progressBar.setMaximum(progress.getiTotle());
		progressBar.setValue(progress.getiProgress());
		return true;
	}

	private JFrame frame;
	private JTextField textField;
	private JPanel panel;

	private void testchart(){
		JChartLibDataSet dataset = createDataset();
		JChartLibBaseChart chart = createChart(dataset);
		JChartLibPanel chartPanel = new JChartLibPanel(chart);
		panel.add(chartPanel, BorderLayout.CENTER);
		chartPanel.setVisible(true);
		
	}
	
	/**
	 * Creates a sample dataset.
	 * 
	 * @return a sample dataset.
	 */
	private JChartLibDataSet createDataset() {
		// Dataseries can be added with int arrays
		int[] values1 = new int[5];
		values1[0] = 1;
		values1[1] = 3;
		values1[2] = 4;
		values1[3] = 7;
		values1[4] = 2;

		// or by generating a Dataserie object
		JChartLibSerie values2 = new JChartLibSerie("非法用户");
		values2.addValue(5);
		values2.addValue(4);
		values2.addValue(2);
		values2.addValue(6);
		values2.addValue(2);

		final JChartLibDataSet dataset = new JChartLibDataSet();
		dataset.addDataSerie("帖子", values1); // adds the apples
		dataset.addDataSerie(values2); // adds the bananas

		return dataset;
	}

	/**
	 * Creates a chart
	 * 
	 * @param dataset
	 *            the data for the chart.
	 * @return a new chart
	 */
	private JChartLibBaseChart createChart(final JChartLibDataSet dataset) {

		// create the chart with title and axis names
		JChartLibLinechart chart = new JChartLibLinechart(
				"论坛攻击情况", // chart title
				"时间", // x axis text
				"广告数量", // y axis text
				dataset // data
				 // legend on
		);

		return chart;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BBSAdmin window = new BBSAdmin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BBSAdmin() {
		initialize();
		testchart();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("论坛管理");
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setBounds(100, 100, 640, 658);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("系统");
		mnNewMenu.setFont(new Font("宋体", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mntmNewMenuItem_1.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mntmNewMenuItem_2.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("帮助");
		mnNewMenu_1.setFont(new Font("宋体", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mntmNewMenuItem_3.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("New menu item");
		mntmNewMenuItem_4.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("New menu item");
		mntmNewMenuItem_5.setFont(new Font("宋体", Font.PLAIN, 16));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.setEditable(false);
		frame.getContentPane().add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(280, 10));
		panel.setMinimumSize(new Dimension(280, 10));
		frame.getContentPane().add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("我的论坛");
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textPane = new JTextPane();
		textPane.setFont(new Font("宋体", Font.PLAIN, 18));
		//panel_1.add(textPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(textPane);
		panel_1.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setFont(new Font("宋体", Font.PLAIN, 16));
		panel_2.setBackground(Color.GRAY);
		panel_2.setSize(new Dimension(32, 32));
		panel_2.setPreferredSize(new Dimension(40, 40));
		panel_2.setMinimumSize(new Dimension(40, 40));
		frame.getContentPane().add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new MigLayout("", "[99px][99px][99px][99px][100px][100px][100px][]", "[32px]"));
		
		JButton btnNewButton = new JButton("日志");
		panel_2.add(btnNewButton, "cell 0 0,grow");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		JButton btnNewButton_1 = new JButton("选项");
		panel_2.add(btnNewButton_1, "cell 1 0,grow");
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton_1.setActionCommand("");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("自动运行");
		panel_2.add(chckbxNewCheckBox, "cell 2 0,grow");
		chckbxNewCheckBox.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton btnNewButton_2 = new JButton("测试");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				test();
			}
		});
		btnNewButton_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel_2.add(btnNewButton_2, "cell 3 0,grow");
		
		JButton btnNewButton_3 = new JButton("关键字");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					KeywordsGUI dialog = new KeywordsGUI();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.loadProp();
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel_2.add(btnNewButton_3, "cell 4 0");
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBorderPainted(false);
		progressBar.setBackground(Color.WHITE);
		panel_2.add(progressBar, "cell 7 0,grow");
		progressBar.setMinimumSize(new Dimension(10, 20));
		progressBar.setFont(new Font("宋体", Font.BOLD, 16));
	}

	public JFrame getFrame() {
		return frame;
	}
}
