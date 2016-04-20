package io.explod.mvpex.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.explod.mvpex.R;
import io.explod.mvpex.ui.activity.BaseActivity;

public class MainActivity extends BaseActivity implements MainView {

	private MainPresenter presenter;

	@Bind(R.id.progress_login)
	ProgressBar mLoginProgress;

	@Bind(R.id.edit_username)
	EditText mUsernameText;

	@Bind(R.id.edit_password)
	EditText mPasswordText;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		presenter = new MainPresenter();
		presenter.attach(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.attach(null);
	}

	@OnClick(R.id.button_submit)
	void onSubmitClick() {
		presenter.onLoginClick();
	}

	@NonNull
	@Override
	public CharSequence getUsername() {
		return mUsernameText.getText();
	}

	@NonNull
	@Override
	public CharSequence getPassword() {
		return mPasswordText.getText();
	}

	@Override
	public void showLoggingInProgressBar() {
		mLoginProgress.setVisibility(View.VISIBLE);

	}

	@Override
	public void hideLoggingInProgressBar() {
		mLoginProgress.setVisibility(View.GONE);
	}

	@Override
	public void showBadLoginSnackbar() {
		showSnackbarMessage(getText(R.string.login_invalid));
	}

	@Override
	public void showLoginOkSnackbar() {
		showSnackbarMessage(getText(R.string.login_complete));
	}

	@Override
	public void showNetworkErrorSnackbar() {
		showSnackbarMessage(getText(R.string.error_network));
	}
}
