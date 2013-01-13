package cn.railsoft.wtk.main.client;

import java.util.ArrayList;

import cn.railsoft.wtk.main.shared.MyO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void greetServer2(MyO myo, AsyncCallback<MyO> callback);
	 @SuppressWarnings("rawtypes") 
	void greetServer3(MyO myo,AsyncCallback<ArrayList> callback);
}
