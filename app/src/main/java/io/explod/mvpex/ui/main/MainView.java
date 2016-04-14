package io.explod.mvpex.ui.main;

import android.support.annotation.NonNull;

import io.explod.mvpex.model.User;
import io.explod.mvpex.util.presenter.PresenterView;

public interface MainView extends PresenterView {

	@NonNull
	CharSequence getUsername();

	@NonNull
	CharSequence getPassword();

	void loggingIn();

	void onBadLogin();

	void onLoginError(@NonNull Throwable t);

	void onLoginSuccessful(@NonNull User user);
}
