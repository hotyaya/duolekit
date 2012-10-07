package cn.rdm.ui.editors;

import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import rdm.dao.Device;
import cn.rdm.biz.DeviceList;

public class DeviceListEditor extends EditorPart {
	private TableViewer tableViewer;
	private Table table;

	public DeviceListEditor() {
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

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
		parent.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(parent, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1);
		gd_composite.widthHint = 578;
		composite.setLayoutData(gd_composite);

		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		tableViewer = new TableViewer(composite_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("New Column");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(200);
		tblclmnNewColumn_1.setText("New Column");

		// TODO Auto-generated method stub
		DeviceListContentProvider dlcp = new DeviceListContentProvider();// 20121006
		DeviceListLabelProvider dllp = new DeviceListLabelProvider();// 20121006
		tableViewer.setContentProvider(dlcp);
		tableViewer.setLabelProvider(dllp);
		// @SuppressWarnings("unused")
		// DeviceList dlist = (DeviceList) this.getAdapter(DeviceList.class);//
		// 20121006
		// Vector<String> v = new Vector<String>();for (int i = 0; i < 100;
		// i++)v.add("" + i);// 20121006
		// tableViewer.setInput(v);
		refreshDeviceList();
	}

	public void refreshDeviceList() {
//		@SuppressWarnings({ "rawtypes" })
//		Vector v = (Vector) this.getEditorInput().getAdapter(Vector.class);
//		if (v != null) {
//			tableViewer.setInput(v);
//			tableViewer.refresh();
//			return;
//		}
		DeviceList list = (DeviceList) this.getEditorInput().getAdapter(
				DeviceList.class);
		if (list != null) {
			tableViewer.setInput(list.test());
			tableViewer.refresh();
			return;
		}
		return;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	class DeviceListContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {
			// TODO Auto-generated method stub

		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub

		}

		@SuppressWarnings("rawtypes")
		@Override
		public Object[] getElements(Object inputElement) {
			// TODO Auto-generated method stub
			if (inputElement instanceof Vector) {
				return ((Vector) inputElement).toArray();
			}
			if (inputElement instanceof Object[]) { // 20121007
				return (Object[]) inputElement;// 20121007
			}// 20121007
			return null;
		}
	}

	class DeviceListLabelProvider implements ITableLabelProvider {

		@Override
		public void addListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub

		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		/**
		 * 这个方法返回的是各列的记录的文字
		 * 参数1:输入的对象
		 * 参数2:列号
		 * 返回值:注意一定要避免Null值,否则出错
		 */
		public String getColumnText(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			// return null;

			switch (columnIndex) {
			case 0:
				if (element instanceof Device) {
					return ((Device)element).getOrgc();
				} else {
					return "测试文字0-" + element.toString();
				}
			case 1:
				if (element instanceof Device) {
					return ((Device)element).getDevid()+"";
				} else {
					return "测试文字1-" + element.toString();
				}
			}
			return "测试文字-1";
		}
	}
}
