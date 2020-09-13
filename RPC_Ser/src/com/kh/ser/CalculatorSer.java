package com.kh.ser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.kh.bean.Calculator;
import com.kh.bean.CalculatorImpl;
import com.kh.bean.CalculatorRPCRequest;

/**
 *  服务器
 * @author Mr.K
 *
 */
public class CalculatorSer {

	private Calculator calculator = new CalculatorImpl();

	public static void main(String[] args) throws IOException {
		new CalculatorSer().run();
	}

	private void run() throws IOException {
		// create server
		ServerSocket sc = new ServerSocket(9090);
		try {
			while (true) {
				// 1. 等待客户端连接
				Socket socket = sc.accept();
				int result = 0;

				try {
					// 将客户发送过来的信息进行反序列化 （使用对象操作流）
					ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
					Object readObject = objectInputStream.readObject();
					System.out.println("request is :" + readObject);
					
					// 调用服务
					// 判断请求对象readObject是否为CalculatorRPCRequest的实例
					if(readObject instanceof CalculatorRPCRequest) {
						//转换object类型
						CalculatorRPCRequest c = (CalculatorRPCRequest) readObject;
						
						//判断是否为add()方法
						if("add".equals(c.getMethod())) {
							// 调用add()方法   得到计算结果result
							result = calculator.add(c.getA(), c.getB());
						} else {
							throw new Exception("尚未存在该"+c.getMethod()+"方法");
						}
					}
					
					//返回结果，同样以流的形式返回
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(new Integer(result));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					socket.close();
				}
			}
		} finally {
			sc.close();
		}
	}

}
