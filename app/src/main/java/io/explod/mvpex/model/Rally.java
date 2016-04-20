package io.explod.mvpex.model;

import java.util.Date;

public class Rally {

	public long id;

	public String name;

	public String location;

	public Date time;

	private static long mockId = 0;

	public static Rally mock() {
		Rally mock = new Rally();
		mock.id = ++mockId;
		mock.name = "Mock Rally " + mock.id;
		mock.location = "Mock Square";
		mock.time = new Date();
		return mock;
	}
}
