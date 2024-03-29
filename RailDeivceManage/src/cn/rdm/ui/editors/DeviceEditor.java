package cn.rdm.ui.editors;

import java.sql.Timestamp;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rdm.dao.Device;
import rdm.dao.DeviceDAO;
import cn.rdm.biz.TimeMillisRandomIdGenerator;

import com.tiff.common.ui.datepicker.DatePickerCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DeviceEditor extends EditorPart {

	private DatePickerCombo datePickerCombo;
	private DatePickerCombo datePickerCombo_1;
	private DatePickerCombo datePickerCombo_2;

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
	private Text text_10;
	private Text text_11;
	private Text text_12;
	private Text text_13;
	private Text text_14;
	private Text text_15;

	public DeviceEditor() {
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			DeviceDAO devicedao = (DeviceDAO) context.getBean("DeviceDAO");
			Device device = new Device();
			device.setOrgc(text.getText().length() > 0 ? text.getText(): "0BJBJXX");
			device.setDevid(new TimeMillisRandomIdGenerator().getIdGenerator());
			int imodc = Integer.parseInt(text_3.getText());
			device.setModc(imodc);
			device.setHldid(0L);
			int ifc = Integer.parseInt(text_4.getText());
			device.setFc(ifc);
			device.setFdate(datePickerCombo.getDate());//new SimpleDateFormat("yyyyMMdd").parse("20121001"));
			device.setFsn(text_5.getText().length()>0?text_5.getText():"923123412341234");
			device.setCs("XD");
			device.setCsli(new TimeMillisRandomIdGenerator().getIdGenerator());
			device.setCsu("刘辉");
			device.setCst(new Timestamp(System.currentTimeMillis()));
			device.setCso("操作员");
			device.setLs("MD");// 上一状态
			device.setLsli(new TimeMillisRandomIdGenerator().getIdGenerator());//92073123L);
			device.setCltag(text_11.getText());
			device.setClc(text_12.getText());// 机车号
			device.setLcd(datePickerCombo_1.getDate());//new SimpleDateFormat("yyyyMMdd").parse("20100909")); // 检修日期
			device.setLcu("检修员");
			device.setLcli(new TimeMillisRandomIdGenerator().getIdGenerator());//22073123L);
			device.setNcpi(new TimeMillisRandomIdGenerator().getIdGenerator());//01073123L);
			device.setNcd(datePickerCombo_2.getDate());//new SimpleDateFormat("yyyyMMdd").parse("20121002"));
			device.setCt1("0");
			device.setCt2("0");
			device.setCt3("0");
			device.setCt4("0");
			device.setCt5("0");
			device.setSt1("0");
			device.setSt2("0");
			device.setSt3("0");
			device.setSt4("0");
			device.setSt5("0");
			device.setVer("version");
			devicedao.save(device);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(4, false));

		Label lblNewLabel = new Label(parent, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("组织代码");

		text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel_1 = new Label(parent, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText("设备ID");

		text_1 = new Text(parent, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblNewLabel_2 = new Label(parent, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_2.setText("设备型号");

		text_3 = new Text(parent, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label lblNewLabel_3 = new Label(parent, SWT.NONE);
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_3.setText("父设备ID");

		text_2 = new Text(parent, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));

		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label.setText("制造厂商");

		text_4 = new Text(parent, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(parent, SWT.NONE);
		
		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doSave(null);
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton.setText("保存");

		Label lblNewLabel_4 = new Label(parent, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_4.setText("制造日期");

		datePickerCombo = new DatePickerCombo(parent, SWT.BORDER);
		datePickerCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_5 = new Label(parent, SWT.NONE);
		lblNewLabel_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_5.setText("制造商串号");

		text_5 = new Text(parent, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_6 = new Label(parent, SWT.NONE);
		lblNewLabel_6.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_6.setText("当前状态");

		Combo combo = new Combo(parent, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1,
				1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label label_1 = new Label(parent, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_1.setText("当前状态日志");

		text_6 = new Text(parent, SWT.BORDER);
		text_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_7 = new Label(parent, SWT.NONE);
		lblNewLabel_7.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_7.setText("操作员");

		text_7 = new Text(parent, SWT.BORDER);
		text_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_8 = new Label(parent, SWT.NONE);
		lblNewLabel_8.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_8.setText("当前状态时间");

		text_8 = new Text(parent, SWT.BORDER);
		text_8.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_9 = new Label(parent, SWT.NONE);
		lblNewLabel_9.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_9.setText("当前状态用户");

		text_9 = new Text(parent, SWT.BORDER);
		text_9.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label label_2 = new Label(parent, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_2.setText("上一状态");

		Combo combo_1 = new Combo(parent, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_10 = new Label(parent, SWT.NONE);
		lblNewLabel_10.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_10.setText("上一状态日志");

		text_10 = new Text(parent, SWT.BORDER);
		text_10.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_11 = new Label(parent, SWT.NONE);
		lblNewLabel_11.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_11.setText("当前位置代码");

		text_11 = new Text(parent, SWT.BORDER);
		text_11.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_12 = new Label(parent, SWT.NONE);
		lblNewLabel_12.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_12.setText("当前位置编号");

		text_12 = new Text(parent, SWT.BORDER);
		text_12.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_13 = new Label(parent, SWT.NONE);
		lblNewLabel_13.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_13.setText("上次检修日期");

		datePickerCombo_1 = new DatePickerCombo(parent, SWT.BORDER);
		datePickerCombo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_14 = new Label(parent, SWT.NONE);
		lblNewLabel_14.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_14.setText("上次检修操作员");

		text_13 = new Text(parent, SWT.BORDER);
		text_13.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_15 = new Label(parent, SWT.NONE);
		lblNewLabel_15.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_15.setText("上次检修日志");

		text_14 = new Text(parent, SWT.BORDER);
		text_14.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label lblNewLabel_16 = new Label(parent, SWT.NONE);
		lblNewLabel_16.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_16.setText("下次检修计划");

		text_15 = new Text(parent, SWT.BORDER);
		text_15.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);

		Label label_3 = new Label(parent, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		label_3.setText("下次检修日期");

		datePickerCombo_2 = new DatePickerCombo(parent, SWT.BORDER);
		datePickerCombo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));
		new Label(parent, SWT.NONE);
		new Label(parent, SWT.NONE);
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
