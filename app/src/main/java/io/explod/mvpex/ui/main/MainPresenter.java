package io.explod.mvpex.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import io.explod.mvpex.model.User;
import io.explod.mvpex.model.request.LoginRequest;
import io.explod.mvpex.network.service.DevelopingService;
import io.explod.mvpex.util.presenter.Presenter;
import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import rx.android.schedulers.AndroidSchedulers;

import static io.explod.mvpex.App.getApp;

public class MainPresenter extends Presenter<MainView> {

	@Inject
	DevelopingService mDevelopingService;

	public MainPresenter() {
		getApp().getObjectGraph().inject(this);
	}

	public void doLogin() {
		MainView view = getView();
		if (view == null) return;

		CharSequence username = view.getUsername();
		CharSequence password = view.getPassword();
		LoginRequest request = new LoginRequest(username, password);

		mDevelopingService.login(request)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				this::onLoginResult,
				this::onLoginError
			);

		view.loggingIn();
	}

	@VisibleForTesting
	void onLoginResult(@NonNull Result<User> result) {
		if (result.isError()) {
			onBadLogin();
		} else {
			Response<User> response = result.response();
			User user = response.body();
			onLoginSuccessful(user);
		}
	}

	@VisibleForTesting
	void onLoginSuccessful(@NonNull User user) {
		MainView view = getActiveView();
		if (view == null) return;
		view.onLoginSuccessful(user);
	}

	@VisibleForTesting
	void onBadLogin() {
		MainView view = getActiveView();
		if (view == null) return;
		view.onBadLogin();
	}

	@VisibleForTesting
	void onLoginError(@NonNull Throwable t) {
		MainView view = getActiveView();
		if (view == null) return;
		view.onLoginError(t);
	}


}
