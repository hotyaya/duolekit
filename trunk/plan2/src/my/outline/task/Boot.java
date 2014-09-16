package my.outline.task;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.hibernate.Session;

import my.outline.dao.HibernateSessionFactory;
import my.outline.gui.DInputTag;

public class Boot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateSessionFactory.getSession();
		DInputTag input = new DInputTag(new Shell(), SWT.ICON_INFORMATION, session);
		input.open();
	}

}
