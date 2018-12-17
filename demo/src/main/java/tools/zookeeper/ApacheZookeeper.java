package tools.zookeeper;

import java.io.IOException;
import java.sql.Date;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.AsyncCallback.StringCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ApacheZookeeper {
	/*ZooKeeper zooKeeper;
	
	public ApacheZookeeper() {
		// TODO Auto-generated constructor stub
		try {
			zooKeeper = new ZooKeeper("117.31.35.1:2181", 5000, new MyWatcher());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		Watcher watcher = new MyWatcher();
		Stat stat = new Stat();
		String NODE_NAME = "/zktest/createNodeApache";
		ZooKeeper zooKeeper = new ZooKeeper("118.31.35.1:2181", 5000, watcher);
		//创建节点，节点存在则抛出异常
//		String path = zooKeeper.create("/zktest/createNodeApache", Thread.currentThread().getName().getBytes(),
//				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//		System.out.println(path);
		
		//获取节点数据
		String result = new String(zooKeeper.getData(NODE_NAME, watcher, stat));
		System.out.println(result);
		System.out.println(new Date(stat.getCtime()));
		
		
		zooKeeper.create(NODE_NAME+"1", Thread.currentThread().getName().getBytes(), 
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,new MyCallBack() , null);
	}
	
	static class MyWatcher implements Watcher{

		public void process(WatchedEvent event) {
			// TODO Auto-generated method stub
			System.out.println("MyWatcher:"+event);
		}
		
	}
	
	static class MyCallBack implements StringCallback{

		public void processResult(int rc, String path, Object ctx, String name) {
			// TODO Auto-generated method stub
			System.out.println(rc+"\t"+path+"\t"+name);
		}
		
	}
}
