package io.explod.mvpex.ui.view.rally;

import android.support.annotation.Nullable;

import io.explod.mvpex.model.Rally;
import io.explod.mvpex.util.presenter.Presenter;

import static io.explod.mvpex.util.DateUtils.formatDateTime;

/* default */ class RallyItemPresenter extends Presenter<RallyItemView> {

	@Nullable
	private final Rally mRally;


	public RallyItemPresenter(@Nullable Rally rally) {
		mRally = rally;
	}

	@Override
	public void attach(@Nullable RallyItemView view) {
		super.attach(view);
		if (view == null) {
			return;
		}
		view.setTitle(mRally == null ? "" : mRally.name);
		view.setLocation(mRally == null ? "" : mRally.location);
		CharSequence dateTimeString = mRally == null ? "" : formatDateTime(mRally.time);
		view.setTime(dateTimeString);
	}
}
