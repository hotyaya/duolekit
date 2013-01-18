package cn.railsoft.schedule.app.client;

import java.sql.Timestamp;
import java.util.Date;

import cn.railsoft.schedule.app.shared.Jobitem;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

public class JobScheduleInput extends DialogBox {

	INotifyJobScheduleChange notify = null;
	Jobitem jobitem = null;
	GreetingServiceAsync greetingService = null;
	final TextBox txtSeq = new TextBox();
	final DateBox dateAction = new DateBox();
	final TextBox txtType = new TextBox();
	final TextBox txtNumNo = new TextBox();
	final TextArea txtContent1 = new TextArea();
	final TextArea txtContent2 = new TextArea();
	final TextBox txtSource = new TextBox();
	final TextArea txtContent3 = new TextArea();
	final TextArea txtContent4 = new TextArea();
	final TextBox txtStatus = new TextBox();
	final TextArea txtContent5 = new TextArea();
	final TextBox txtTag = new TextBox();
	final TextBox textBox_16 = new TextBox();

	/**
	 * 打开窗体
	 * 根据是否有对象，没有的视为新建，有的赋值；
	 * 
	 */
	public void showInput() {
		if (jobitem == null) {
			txtSeq.setText("");
			dateAction.setValue(new Date());
			txtType.setText("");
			txtNumNo.setText("");
			txtContent1.setText("");
			txtContent2.setText("");
			txtSource.setText("");
			txtContent3.setText("");
			txtContent4.setText("");
			txtStatus.setText("");
			txtContent5.setText("");
			txtTag.setText("");
			textBox_16.setText("");		
			greetingService.getSeq("JobItem", new AsyncCallback<Long>() {
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(Long result) {
					// TODO Auto-generated method stub
					txtSeq.setText((result + 1) + "");
				}
			});
		} else {
			
			
		}
		this.center();
	}

	public void saveJobItem() {
		Jobitem item = new Jobitem();
		// item.setId(ji.getId());
		item.setCreateTime(new Timestamp(System.currentTimeMillis()));
		item.setUserid(0);
		item.setSeq(Long.parseLong(txtSeq.getText()));
		java.util.Date d = dateAction.getValue();
		DateTimeFormat format = DateTimeFormat.getFormat("yyyyMMdd");
		String s = format.format(d);
		item.setActionDate(Integer.parseInt(s));
		item.setType(txtType.getText());
		item.setNumPrefix("");
		item.setNumNo(txtNumNo.getText());
		item.setNumSuffix("");
		item.setContent1(txtContent1.getText());
		item.setContent2(txtContent2.getText());
		item.setContent3(txtContent3.getText());
		item.setContent4(txtContent4.getText());
		item.setContent5(txtContent5.getText());
		item.setSource("李科");
		item.setSourceId("9238723713");
		item.setStatus("待办");
		item.setStatusCreatetime(new Timestamp(System.currentTimeMillis()));
		item.setMemo("");
		greetingService.saveJobItem(item, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(String result) {
				if (result.equals("SUCCESS")) {
					if (notify != null) {
						notify.notifyJobScheduleChange();
					}
					Window.alert("成功！");
				} else {
					Window.alert("失败！");
				}
			}
		});

	}

	/**
	 * @wbp.parser.constructor
	 */
	public JobScheduleInput(GreetingServiceAsync greetingService,
			INotifyJobScheduleChange notify) {
		this.greetingService = greetingService;
		this.notify = notify;
		init();
	}

	private void init() {
		this.setWidth("720px");
		this.setHeight("480px");
		FlexTable flexTable = new FlexTable();
		flexTable.setSize("710px", "470px");

		final Label lblNewLabel = new Label("序号");
		lblNewLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(0, 0, lblNewLabel);

		txtSeq.setEnabled(false);
		flexTable.setWidget(0, 1, txtSeq);

		final Label lblNewLabel_10 = new Label("收文日期");
		lblNewLabel_10
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(0, 2, lblNewLabel_10);

		flexTable.setWidget(0, 3, dateAction);
		dateAction.setWidth("120px");

		Button btnNewButton = new Button("New button");
		btnNewButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hideMe();
			}
		});
		btnNewButton.setText("关闭");
		flexTable.setWidget(0, 4, btnNewButton);

		final Label lblNewLabel_1 = new Label("事项分类");
		lblNewLabel_1
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(1, 0, lblNewLabel_1);

		flexTable.setWidget(1, 1, txtType);

		final Label lblNewLabel_11 = new Label("文号、电报号");
		lblNewLabel_11
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(1, 2, lblNewLabel_11);

		flexTable.setWidget(1, 3, txtNumNo);
		txtNumNo.setWidth("198px");

		Button btnNewButton_1 = new Button("New button");
		btnNewButton_1.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				saveJobItem();
			}
		});
		btnNewButton_1.setText("保存");
		flexTable.setWidget(1, 4, btnNewButton_1);

		final Label lblNewLabel_2 = new Label("事项");
		lblNewLabel_2
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(2, 0, lblNewLabel_2);

		flexTable.setWidget(2, 1, txtContent1);
		txtContent1.setWidth("570px");

		final Label lblNewLabel_12 = new Label("领导批示");
		lblNewLabel_12
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(3, 0, lblNewLabel_12);

		flexTable.setWidget(3, 1, txtContent2);
		txtContent2.setWidth("240px");

		final Label lblNewLabel_3 = new Label("来源");
		lblNewLabel_3
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(4, 0, lblNewLabel_3);

		flexTable.setWidget(4, 1, txtSource);
		txtSource.setWidth("240px");

		final Label lblNewLabel_13 = new Label("科长意见");
		lblNewLabel_13
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(5, 0, lblNewLabel_13);

		flexTable.setWidget(5, 1, txtContent3);
		txtContent3.setWidth("240px");

		final Label lblNewLabel_4 = new Label("情况摘要");
		lblNewLabel_4
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(6, 0, lblNewLabel_4);

		flexTable.setWidget(6, 1, txtContent4);
		txtContent4.setWidth("240px");

		final Label lblNewLabel_14 = new Label("当前状态");
		lblNewLabel_14
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(7, 0, lblNewLabel_14);

		flexTable.setWidget(7, 1, txtStatus);
		txtStatus.setWidth("240px");

		final Label lblNewLabel_6 = new Label("20130118");
		lblNewLabel_6
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(7, 2, lblNewLabel_6);

		final Label lblNewLabel_5 = new Label("结论");
		lblNewLabel_5
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(8, 0, lblNewLabel_5);

		flexTable.setWidget(8, 1, txtContent5);
		txtContent5.setWidth("240px");

		final Label lblNewLabel_15 = new Label("标签");
		lblNewLabel_15
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(9, 0, lblNewLabel_15);

		flexTable.setWidget(9, 1, txtTag);

		final Label lblNewLabel_16 = new Label("IP地址");
		lblNewLabel_16
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		flexTable.setWidget(9, 2, lblNewLabel_16);

		textBox_16.setEnabled(false);
		flexTable.setWidget(9, 3, textBox_16);
		flexTable.getFlexCellFormatter().setColSpan(2, 1, 3);

		final Label lblNewLabel_7 = new Label("新事项");
		lblNewLabel_7
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(10, 0, lblNewLabel_7);

		final Label lblNewLabel_8 = new Label("New label");
		lblNewLabel_8
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(10, 1, lblNewLabel_8);

		final Label lblNewLabel_9 = new Label("New label");
		lblNewLabel_9
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(10, 2, lblNewLabel_9);

		final Label lblNewLabel_17 = new Label("New label");
		lblNewLabel_17
				.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.setWidget(10, 3, lblNewLabel_17);
		this.add(flexTable);
	}

	private void hideMe() {
		hide();
	}

}
