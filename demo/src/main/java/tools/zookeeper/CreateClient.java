package tools.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * 使用zkClient操作zookeeper，前提是父节点一定要存在，如果父节点不存在，可以调用
 * @author h15039
 *
 */
public class CreateClient implements Runnable{
	private static final String NODE_NAME = "/zktest/createNode";
	private ZkClient client = new ZkClient("118.31.35.1:2181",5000);
	
	public static void main(String[] args) {
		for(int i=0;i<2;i++) {
			new Thread(new CreateClient()).start();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
//		client.createPersistent(NODE_NAME, true);
		String path;
		try {
			//创建临时节点
//			path = client.create(NODE_NAME, Thread.currentThread().getName(), CreateMode.EPHEMERAL);
			//创建持久节点
			path = client.create(NODE_NAME, Thread.currentThread().getName(), CreateMode.PERSISTENT);
		}catch(Exception e) {
			path = null;
		}
		System.out.println(Thread.currentThread().getName()+" created node:"+path);
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Thread:"+Thread.currentThread().getName()+" exit!");
	}
}
