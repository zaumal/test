package com.zaumal.spi.service;

import com.zaumal.spi.IParseDoc;

public class ExcelParse implements IParseDoc {
	@Override
	public void parse() {
		System.out.println("excel parse");
	}

}
