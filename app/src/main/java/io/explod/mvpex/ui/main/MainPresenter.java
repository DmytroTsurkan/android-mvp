package io.explod.mvpex.ui.main;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import javax.inject.Inject;

import io.explod.mvpex.model.User;
import io.explod.mvpex.model.request.LoginRequest;
import io.explod.mvpex.network.service.DevelopingService;
import io.explod.mvpex.util.presenter.RxPresenter;
import rx.android.schedulers.AndroidSchedulers;

import static io.explod.mvpex.App.getApp;
import static io.explod.mvpex.util.network.NetworkUtils.isNetworkError;

public class MainPresenter extends RxPresenter<MainView> {

	@Inject
	DevelopingService mDevelopingService;

	public MainPresenter() {
		getApp().getObjectGraph().inject(this);
	}

	public void doLogin() {
		MainView view = getView();
		if (view == null) return;

		view.closeKeyboard();
		view.showLoggingInProgressBar();

		CharSequence username = view.getUsername();
		CharSequence password = view.getPassword();

		LoginRequest request = new LoginRequest(username, password);

		bind(mDevelopingService.login(request)
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(
				this::onLogin,
				this::onLoginError
			));
	}

	@VisibleForTesting
	void onLogin(@NonNull User user) {
		MainView view = getActiveView();
		if (view == null) return;

		view.hideLoggingInProgressBar();
		view.showLoginOkSnackbar();
	}

	@VisibleForTesting
	void onLoginError(@NonNull Throwable t) {
		MainView view = getActiveView();
		if (view == null) return;

		view.hideLoggingInProgressBar();
		if (isNetworkError(t)) {
			view.showNetworkErrorSnackbar();
		} else {
			view.showBadLoginSnackbar();
		}
	}


}
