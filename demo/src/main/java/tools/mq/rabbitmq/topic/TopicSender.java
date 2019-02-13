package tools.mq.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicSender {
	private final static String QUEUE_NAME = "topic_queue";
	
	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try {
			Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    String message = "Hello World!";
		    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		    System.out.println(" [x] Sent '" + message + "'");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
