package com.kh.bean;

//基于接口   计算器的实现类
public class CalculatorImpl implements Calculator {

	@Override
	public int add(int a, int b) {
		return a+b;
	}

}
