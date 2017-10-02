package com.app.factory;

import com.app.controller.AppController;

public class AppControllerFactory {
	public static AppController getAppController() {
		return new AppController();
	}
}
