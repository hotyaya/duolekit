package cn.rdm;

import java.awt.Toolkit;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1024, 768));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(false);
    }

	@Override
	public void postWindowCreate() {
		super.postWindowCreate();
		Toolkit kit = Toolkit.getDefaultToolkit();    
		getWindowConfigurer().getWindow().getShell().setLocation((kit.getScreenSize().width - 1024) / 2, (kit    
	            .getScreenSize().height - 768) / 2);//屏中
		getWindowConfigurer().getWindow().getShell().setMaximized(true);//全屏
	}
    
}
