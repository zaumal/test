package com.zaumal.test.wf.controller;

import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaumal.test.wf.service.TestService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TestController {
	@Autowired
	private TestService testService;
	
	@GetMapping("/t1")
	public String t1() {
		System.out.println("TestController t1 start");
		String id = UUID.randomUUID().toString();
		System.out.println("TestController t1 before-return");
		return id;
	}
	
	@GetMapping("/t2")
	public String t2() {
		System.out.println("TestController t2 start");
		String id = testService.sleepThenReturn(4);
		System.out.println("TestController t2 before-return");
		return id;
	}
	
	@GetMapping("/t3")
	public Mono<String> t3() {
		System.out.println("TestController t3 start");
		Mono<String> result = Mono.fromSupplier(() -> testService.sleepThenReturn(4));
		System.out.println("TestController t3 before-return");
		return result;
	}
	
	@GetMapping("/t4")
	public Flux<String> t4(){
		System.out.println("TestController t4 start");
		Flux<String> result = Flux.fromStream(IntStream.range(0, 10).mapToObj(x -> testService.sleepThenReturn(5)));
		System.out.println("TestController t4 before-return");
		return result;
	}
}
