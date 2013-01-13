package cn.railsoft.wtk.main.client;

import java.util.ArrayList;

import cn.railsoft.wtk.main.shared.MyO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	MyO greetServer2(MyO myo) throws IllegalArgumentException;
	@SuppressWarnings("rawtypes")
	ArrayList greetServer3(MyO myo) throws IllegalArgumentException;
}
