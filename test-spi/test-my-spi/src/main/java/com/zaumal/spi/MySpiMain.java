package com.zaumal.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class MySpiMain {
	public static void main(String[] args) {
		System.out.println("===========spi===========");
		
		ServiceLoader<IParseDoc> loader = ServiceLoader.load(IParseDoc.class);
		
		Iterator<IParseDoc> it = loader.iterator();
		
		while(it.hasNext()) {
			it.next().parse();
		}
	}
}
