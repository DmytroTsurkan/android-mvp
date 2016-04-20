package io.explod.mvpex.ui.activity;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import io.explod.mvpex.util.presenter.PresenterView;
import io.explod.mvpex.util.snackbar.SnackbarUtil;
import rx.functions.Action0;

import static io.explod.mvpex.util.TagUtils.makeTag;

public abstract class BaseActivity extends AppCompatActivity implements PresenterView {

	private static final String TAG = makeTag(BaseActivity.class);

	@Override
	public boolean isActivelyReceivingCallbacks() {
		return !isFinishing() && !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isDestroyed());
	}

	protected void showSnackbarMessage(@NonNull CharSequence title) {
		showSnackbar(title, null, null, null);
	}

	protected void showSnackbar(@NonNull CharSequence title, @Nullable CharSequence actionTitle, @Nullable Action0 onAction, @Nullable Action0 onCompleted) {
		View view = findViewById(android.R.id.content);
		if (view == null) {
			Log.wtf(TAG, "Activity content view not found");
		} else {
			SnackbarUtil.show(view, title, actionTitle, onAction, onCompleted);
		}
	}

	public void closeKeyboard() {
		View view = getCurrentFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

}
