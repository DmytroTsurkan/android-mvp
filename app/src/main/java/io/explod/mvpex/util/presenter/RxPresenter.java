package io.explod.mvpex.util.presenter;

import android.support.annotation.Nullable;

import rx.Subscription;
import rx.internal.util.SubscriptionList;

/**
 * Presenter that unsubscribes from subscription bound to the view when the view changes
 */
public abstract class RxPresenter<T extends PresenterView> extends Presenter<T> {

	@Nullable
	SubscriptionList mSubscriptionList;

	@Override
	public void useView(@Nullable T view) {
		unsubscribe();
		super.useView(view);
	}

	protected synchronized void bind(Subscription subscription) {
		if (mSubscriptionList == null) {
			mSubscriptionList = new SubscriptionList();
		}
		mSubscriptionList.add(subscription);
	}

	private synchronized void unsubscribe() {
		if (mSubscriptionList == null) return;
		if (!mSubscriptionList.isUnsubscribed()) {
			mSubscriptionList.unsubscribe();
		}
		mSubscriptionList = null;
	}
}
