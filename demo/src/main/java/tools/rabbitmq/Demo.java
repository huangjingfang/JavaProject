package tools.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Demo {
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		// "guest"/"guest" by default, limited to localhost connections
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setVirtualHost("rd10");
		factory.setHost("120.26.125.17");
		factory.setPort(5672);

		Connection conn = factory.newConnection();
		System.out.println(conn.isOpen());
		
		Channel channel = conn.createChannel();
		channel.exchangeDeclare("smarthome.rpc.exchange", "direct", true);
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, "smarthome.rpc.queue", "smarthome.netty.rpc.routekey");
		
	}
}
