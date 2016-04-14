package io.explod.mvpex.meta;

import android.test.AndroidTestCase;

import io.explod.mvpex.module.modules.MockModule;

public class MVPAndroidTestCase extends AndroidTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockModule.getMockMode().setEnabled(true);
	}
}
