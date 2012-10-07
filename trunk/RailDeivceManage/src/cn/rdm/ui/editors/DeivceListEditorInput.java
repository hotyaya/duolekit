package cn.rdm.ui.editors;

import java.util.Vector;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import cn.rdm.biz.DeviceList;

public class DeivceListEditorInput implements IEditorInput {
	
	@SuppressWarnings("unused")
	private DeviceList devicelist = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		Vector vtest = new Vector();for (int i = 0; i < 20; i++)vtest.add("" + i);// 20121006
		if (adapter.equals(Vector.class)){	//20121007用于测试
			return vtest;
		}else if (adapter.equals(DeviceList.class)){
			return new DeviceList();
		}
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "设备表";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "设备表";
	}

}
