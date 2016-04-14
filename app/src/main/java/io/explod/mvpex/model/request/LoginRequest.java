package io.explod.mvpex.model.request;

import android.support.annotation.NonNull;

public class LoginRequest {

	CharSequence username;

	CharSequence password;

	public LoginRequest(@NonNull CharSequence username, @NonNull CharSequence password) {
		this.username = username;
		this.password = password;
	}
}
