package cn.railsoft.simulate.service;

public class ClientDispatcher {
	ClientResourcePool clientpool = new ClientResourcePool();

	public void test() {
		String client = clientpool.getUsableClient();
		if (!client.equals("NULL")) {
			// 发送到客户端
			
		}
	}
}
