package com.zaumal.test.demo2.fegin.fallback;

import org.springframework.stereotype.Component;

import com.zaumal.test.demo2.fegin.client.Demo3Client;

@Component
public class Demo3Fallback implements Demo3Client {
	@Override
	public String t3(String p1) {
		return "[demo3fallback > t3 > " + p1 + "]";
	}

}
