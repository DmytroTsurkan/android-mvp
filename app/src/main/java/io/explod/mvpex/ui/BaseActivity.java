package io.explod.mvpex.ui;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import io.explod.mvpex.util.presenter.PresenterView;

public class BaseActivity extends AppCompatActivity implements PresenterView {

	@Override
	public boolean isActivelyReceivingCallbacks() {
		return !isFinishing() && !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isDestroyed());
	}

}
