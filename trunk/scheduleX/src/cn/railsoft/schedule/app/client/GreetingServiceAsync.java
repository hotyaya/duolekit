package cn.railsoft.schedule.app.client;

import java.util.ArrayList;

import cn.railsoft.schedule.app.shared.Jobitem;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void getJobItemList(String filter,AsyncCallback<ArrayList<Jobitem>> callback);

	void getSeq(String seqname, AsyncCallback<Long> callback);

	void saveJobItem(Jobitem jobitem, AsyncCallback<String> callback);
}
