package com.zaumal.test.containerInitializer.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class StartupListener implements ServletRequestListener{
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("StartupListener requestDestroyed...");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("StartupListener requestInitialized...");
	}
}
