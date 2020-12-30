package t.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import t.producer.TestProducer;

@RestController
public class TestController {
	@Autowired
	private TestProducer testProducer;
	@GetMapping("/t1/{v1}")
	public String t1(@PathVariable String v1) {
		testProducer.t1();
		return v1;
	}
}
