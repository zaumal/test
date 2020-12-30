package com.google.common.hash;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.Charsets;

public class TestBloomFilter {
	private static int count = 5000000;
	private static long n1 = 13800000000L;
	private static long n2 = 13900000000L;
	private static long n3 = 13500000000L;
	private static BloomFilter<String> bf1 = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), count);
	private static BloomFilter<String> bf2 = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), count);
	private static BloomFilter<String> bf3 = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), count);
	
	Integer count1 = 0;
	Integer count2 = 0;
	Integer count3 = 0;
	
	@Test
	public void t1() {
		for(int i = 0; i < count; i++) {
			bf1.put((n1+i) + "");
			bf2.put((n2+i) + "");
			bf3.put((n3+i) + "");
		}
		
		long size = bf1.bitSize() + bf2.bitSize() + bf3.bitSize();
		
		System.out.println(tra(count*3) + "个手机号占用 : " + size/1024/1024 + "MB(" + size + ")");
		
		Set<String> nums = new HashSet<>();
		for(int i = 0; i < 100; i++) {
			if(i % 2 == 0) {
				nums.add((n2 + i) + "");
			}else if(i % 3 == 0) {
				nums.add((n3 + i) + "");
			}else {
				nums.add((n1 + i) + "");
			}
		}
		
		match(nums);
	}
	
	private void match(Collection<String> nums) {
		long start = System.currentTimeMillis();
		
		nums.forEach(num -> {
			boolean has1 =bf1.mightContain(num);
			boolean has2 =bf2.mightContain(num);
			boolean has3 =bf3.mightContain(num);
			if(has1) {
				count1++;
			}
			if(has2) {
				count2++;
			}
			if(has3) {
				count3++;
			}
			if(has1 && has2) {
				System.out.println("1&2 : " + num);
			}
			if(has2 && has3) {
				System.out.println("2&3 : " + num);
			}
			if(has1 && has3) {
				System.out.println("1&3 : " + num);
			}
			if(has1 && has2 && has3) {
				System.out.println("1&2&3 : " + num);
			}
		});
		
		long end = System.currentTimeMillis();
		
		long v = end - start;
		System.out.println(tra(nums.size()) + "次查询总用时：" + v + "ms (平均每次" + v*1.0/nums.size() + "ms)");
		
		System.out.println("count1 : " + count1 + "; count2 : " + count2 + "; count3 : " + count3);
	}
	
	private String tra(long n) {
		long m = n/10000;
		if(m > 1) {
			return m + "万";
		}else {
			return n + "";
		}
	}
}
