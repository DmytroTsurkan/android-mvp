package io.explod.mvpex.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.explod.mvpex.R;
import io.explod.mvpex.model.User;
import io.explod.mvpex.ui.BaseActivity;

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

		if (presenter == null)
			presenter = new MainPresenter();
		presenter.useView(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.useView(null);
		if (isFinishing()) {
			presenter = null;
		}
	}

	@OnClick(R.id.button_submit)
	void onSubmitClick() {
		presenter.doLogin();
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
	public void loggingIn() {
		mLoginProgress.setVisibility(View.VISIBLE);
	}

	private void loginComplete() {
		mLoginProgress.setVisibility(View.GONE);
	}

	@Override
	public void onBadLogin() {
		loginComplete();
		Toast.makeText(this, R.string.login_invalid, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onLoginError(@NonNull Throwable t) {
		loginComplete();
		Toast.makeText(this, R.string.error_network, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onLoginSuccessful(@NonNull User user) {
		loginComplete();
		Toast.makeText(this, R.string.login_complete, Toast.LENGTH_LONG).show();
	}
}
