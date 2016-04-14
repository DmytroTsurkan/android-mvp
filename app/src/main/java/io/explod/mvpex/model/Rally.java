package io.explod.mvpex.model;

import java.util.Date;

public class Rally {

	public String name;

	public String location;

	public Date time;

	public static Rally mock() {
		Rally mock = new Rally();
		mock.name = "Mock Rally";
		mock.location = "Mock Square";
		mock.time = new Date();
		return mock;
	}
}
