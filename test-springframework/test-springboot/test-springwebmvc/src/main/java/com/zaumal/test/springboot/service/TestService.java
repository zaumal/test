package com.zaumal.test.springboot.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TestService {
	public String get() {
		return "test springboot " + UUID.randomUUID().toString();
	}
}
