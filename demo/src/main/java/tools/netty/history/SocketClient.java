package tools.netty.history;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {
	public static void main(String[] args) throws IOException {
		// 要连接的服务端IP地址和端口
		String host = "127.0.0.1";
		int port = 55533;
		// 与服务端建立连接
		Socket socket = new Socket(host, port);
		// 建立连接后获得输出流
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
		String message = "你好  yiwangzhibujian";
		writer.write(message);
		
		// 通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
		socket.shutdownOutput();

		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
		StringBuilder sb = new StringBuilder();
		while (reader.ready()) {
			// 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
			String line = reader.readLine();
			sb.append(line);
		}
		System.out.println("get message from server: " + sb);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reader.close();
		writer.close();
		socket.close();
	}
}
