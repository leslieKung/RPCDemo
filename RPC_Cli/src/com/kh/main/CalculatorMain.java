package com.kh.main;

import com.kh.bean.Calculator;
import com.kh.cli.CalculatorCli;

public class CalculatorMain {
	
	public static void main(String[] args) throws Exception {
		Calculator c = new CalculatorCli();
		int result = c.add(1, 2);
		System.out.println("result is : " + result);	
	}

}
