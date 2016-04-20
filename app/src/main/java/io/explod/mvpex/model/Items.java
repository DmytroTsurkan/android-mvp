package io.explod.mvpex.model;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Func1;

public class Items<T> {

	public List<T> collection;

	public static <T> Items<T> mock(Func1<Integer, T> factory, int n) {
		Items<T> mock = new Items<>();
		List<T> items = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			T item = factory.call(n);
			items.add(item);
		}
		mock.collection = items;
		return mock;
	}
}
