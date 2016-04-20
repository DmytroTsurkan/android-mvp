package io.explod.mvpex.model;

import android.support.annotation.NonNull;

import java.util.Date;

public class Rally {

	public long id;

	public String name;

	public String location;

	public Date time;

	private static long mockId = 0;

	public static Rally mock() {
		Rally mock = new Rally();
		mockSetNameForId(mock, ++mockId);
		mock.location = "Mock Square";
		mock.time = new Date();
		return mock;
	}

	public static void mockSetNameForId(@NonNull Rally rally, long id) {
		rally.id = id;
		rally.name = "Mock Rally " + id;
	}
}
