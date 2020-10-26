package com.zaumal.test.demo1.feign.fallback;

import org.springframework.stereotype.Component;

import com.zaumal.test.demo1.feign.clien.Demo2Clien;

@Component
public class Demo2Fallback implements Demo2Clien {
	@Override
	public String t2(String p1) {
		return "[demo2fallback > t2 > " + p1 + "]";
	}

	@Override
	public String t3(String p1) {
		return "[demo2fallback > t3 > " + p1 + "]";
	}

}
