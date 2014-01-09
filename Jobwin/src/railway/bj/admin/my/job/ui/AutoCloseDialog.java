package railway.bj.admin.my.job.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class AutoCloseDialog extends Dialog implements Runnable {
    private Thread thread;
    protected Shell shell;
    protected Display display = getParent().getDisplay();
    
    public static final int ERROR = SWT.ICON_ERROR;
    public static final int INFORMATION = SWT.ICON_INFORMATION;
    public static final int QUESTION = SWT.ICON_QUESTION;
    public static final int WARNING = SWT.ICON_WARNING;
    
    private int dialog_width = 400;//对话框宽度
    private int dialog_height = 130;//对话框高度
    private int kind = INFORMATION;//对话框类型
    private String message = "";//要显示的内容
    private long holdTime = 0;//对话框显示时间（不含隐藏时间）
    private long alphaTime = 2;//渐出单位时间（总时间要*255）
    
    
    // 各种构造方法
    public AutoCloseDialog(Shell parent, int style, String message) {
        super(parent, style);
        this.message = message;
    }
    public AutoCloseDialog(Shell parent, int style, String message, Integer kind) {
        this(parent, style, message);
        if (kind != null && (kind == ERROR || kind == INFORMATION || kind == QUESTION || kind == WARNING)) {
        	this.kind = kind;
		}
    }
    public AutoCloseDialog(Shell parent, int style, String message, Integer kind, Long holdTime) {
        this(parent, style, message, kind);
        if (holdTime != null) {
        	this.holdTime = holdTime;
        }
    }
    public AutoCloseDialog(Shell parent, int style, String message, Integer kind, Long holdTime, Long alphaTime) {
        this(parent, style, message, kind, holdTime);
        if (alphaTime != null) {
        	this.alphaTime = alphaTime;
        }
    }
    public AutoCloseDialog(Shell parent, int style, String message, Integer kind, Long holdTime, Long alphaTime, 
    		Integer dialog_width, Integer dialog_height) {
    	this(parent, style, message, kind, holdTime, alphaTime);
    	if (dialog_width != null) {
			this.dialog_width = dialog_width;
		}
    	if (dialog_height != null) {
			this.dialog_height = dialog_height;
		}
    }
    /**
     *2013-7-5/上午09:55:59
     *@user:刘凯
     *@desc:打开对话框方法
     */
    public void open() {
        createContents();
        thread = new Thread(this);
        thread.start();
        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
    }
    /**
     *2013-7-5/上午09:56:13
     *@user:刘凯
     *@desc:创建对话框面板
     */
    private void createContents() {
    	String title = "";
    	switch (kind) {
		case ERROR:
			title = "错误提示";
			break;
		case INFORMATION:
			title = "信息提示";
			break;
		case QUESTION:
			title = "确认提示";
			break;
		case WARNING:
			title = "警告提示";
			break;
		}
        shell = new Shell(getParent(), SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
        shell.setSize(dialog_width, dialog_height);
        shell.setText(title);
        shell.setLayout(new GridLayout(2, false));
        Canvas canvas = new Canvas(shell, SWT.NONE);
        canvas.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.drawImage(getSWTImage(kind), 20, 20);
			}
		});
        Label label = new Label(shell, SWT.NONE);
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true));
        label.setFont(new Font(shell.getDisplay(), "宋体", 10, SWT.NORMAL));
        label.setText(message);
        
        Button button = new Button(shell, SWT.NONE);
        GridData buttonGD = new GridData(SWT.RIGHT, SWT.CENTER, true, true, 2, 1);
        buttonGD.widthHint = 100;
        buttonGD.heightHint = 30;
        button.setLayoutData(buttonGD);
        button.setText("确定");
        button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
				super.widgetSelected(e);
			}
		});
    }
    /**
     *2013-7-5/上午09:54:16
     *@user:刘凯
     *@desc:获取图片
     */
    private Image getSWTImage(int imageID) {
    	return shell.getDisplay().getSystemImage(imageID);
    }
    /**
     *2013-7-5/上午09:54:55
     *@user:刘凯
     *@desc:关闭方法
     */
    private void close() {
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
            	try {
            		for (int i = 255; i > 0; i--) {
            			Thread.sleep(alphaTime);
    					shell.setAlpha(i);
    				}
				} catch (Exception e) {
				}
            	if (!shell.isDisposed()) {
            		shell.close();
                    shell.dispose();
				}
            }
        });
    }
    /**
     *2013-7-5/上午09:54:55
     *@user:刘凯
     *@desc:休眠方法
     */
    public void run() {
        try {
			Thread.sleep(holdTime);
		} catch (Exception e) {
		}
        close();
    }
    
    public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		shell.setSize(600, 700);
		shell.open();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		AutoCloseDialog autoCloseDialog = new AutoCloseDialog(shell, SWT.NONE, "错误信息提示", null , 2000l);
		autoCloseDialog.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}
}