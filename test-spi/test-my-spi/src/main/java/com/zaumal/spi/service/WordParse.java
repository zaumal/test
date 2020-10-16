package com.zaumal.spi.service;

import com.zaumal.spi.IParseDoc;

public class WordParse implements IParseDoc {
	@Override
	public void parse() {
		System.out.println("word parse");
	}

}
