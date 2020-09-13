package com.kh.cli;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.kh.bean.Calculator;
import com.kh.bean.CalculatorRPCRequest;

public class CalculatorCli implements Calculator{
	
	public static final int PORT = 9090;  //端口
	public static final String url = "127.0.0.1";

	@Override
	public int add(int a, int b) throws Exception {
		Socket socket = null;

		try {
			//创建客户端
			socket = new Socket(url,PORT);
			
			//设置信息
			CalculatorRPCRequest cr = new CalculatorRPCRequest();
			cr.setA(a);
			cr.setB(b);
			cr.setMethod("add");
			
			//将对象序列化后传送给服务器
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(cr);
			
			//接收服务器反馈的信息，反序列化
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Object readObject = ois.readObject();
			System.out.println("服务器响应的信息："+readObject);
			return (Integer) readObject;
			
		} catch (Exception e) {
			throw new Exception("客户端错误");
		} finally {
			socket.close();
		}	
	}

}
