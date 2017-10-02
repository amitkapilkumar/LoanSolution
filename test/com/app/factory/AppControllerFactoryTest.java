package com.app.factory;

import org.junit.Assert;
import org.junit.Test;

public class AppControllerFactoryTest {

	@Test
	public void testGetAppController() {
		Assert.assertNotNull(AppControllerFactory.getAppController());
	}
}