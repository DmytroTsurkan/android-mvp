package io.explod.mvpex.model;

import java.util.ArrayList;
import java.util.List;

public class Items<T> {

	public List<T> collection;

	public static <T> Items<T> mock(T fill, int n) {
		Items<T> mock = new Items<>();
		List<T> items = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			items.add(fill);
		}
		mock.collection = items;
		return mock;
	}
}
