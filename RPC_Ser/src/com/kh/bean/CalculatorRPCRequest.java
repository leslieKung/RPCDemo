package com.kh.bean;

import java.io.Serializable;

/**
 * RPC请求对象，需要完成序列化操作
 * @author Mr.K
 *
 */
public class CalculatorRPCRequest implements Serializable {

	/**
	 * 生成序列化id
	 */
	private static final long serialVersionUID = -9213310144003076599L;

	private String method;
	private int a;
	private int b;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

}
