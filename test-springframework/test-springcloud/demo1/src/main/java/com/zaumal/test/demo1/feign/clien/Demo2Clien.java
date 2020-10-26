package com.zaumal.test.demo1.feign.clien;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zaumal.test.demo1.feign.fallback.Demo2Fallback;

@FeignClient(name = "demo2",fallback = Demo2Fallback.class)
public interface Demo2Clien {
	@GetMapping("/demo2/t2/{p1}")
	public String t2(@PathVariable("p1") String p1); //参数p1需要显示声明，不然启动时报错

	@GetMapping("/demo2/t3/{p1}")
	public String t3(@PathVariable("p1") String p1);
}
