package com.zaumal.test.demo2.fegin.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zaumal.test.demo2.fegin.fallback.Demo3Fallback;
import com.zaumal.test.demo2.fegin.rule.RibbonConfig;

@FeignClient(name = "demo3", fallback = Demo3Fallback.class, configuration = RibbonConfig.class)
public interface Demo3Client {
	@GetMapping("/demo3/t3/{p1}")
	public String t3(@PathVariable("p1") String p1);
}
