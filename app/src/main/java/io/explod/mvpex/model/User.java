package io.explod.mvpex.model;

public class User {

	String username;

	String fullName;

	String email;

	public static User mock() {
		User mock = new User();
		mock.username = "mock-user";
		mock.fullName = "Mockity McMockerton";
		mock.email = "mock@example.com";
		return mock;
	}
}
