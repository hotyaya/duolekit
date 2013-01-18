package cn.railsoft.schedule.app.client;

import java.util.ArrayList;

import cn.railsoft.schedule.app.shared.Jobitem;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	ArrayList<Jobitem> getJobItemList(String filter)  throws Exception;
	Long getSeq(String seqname) throws Exception;
	String saveJobItem(Jobitem jobitem)throws Exception;
}
