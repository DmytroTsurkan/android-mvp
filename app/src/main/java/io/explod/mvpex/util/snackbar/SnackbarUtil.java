package io.explod.mvpex.util.snackbar;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import rx.functions.Action0;

public class SnackbarUtil {

	@NonNull
	public static Snackbar show(@NonNull View anchor, @NonNull CharSequence title, @Nullable CharSequence actionTitle, @Nullable Action0 onAction, @Nullable Action0 onCompleted) {
		Snackbar snackbar = Snackbar.make(anchor, title, Snackbar.LENGTH_LONG);
		if (actionTitle != null) {
			snackbar.setAction(actionTitle, (v) -> {
				if (onAction != null) {
					onAction.call();
				}
			});
		}
		onSnackbarComplete(snackbar, onCompleted);
		snackbar.show();
		return snackbar;
	}

	private static void onSnackbarComplete(@NonNull Snackbar snackbar, @Nullable Action0 onCompleted) {
		if (onCompleted == null) return;
		Snackbar.Callback onSnackbarCompleted = new Snackbar.Callback() {
			@Override
			public void onDismissed(Snackbar snackbar, int event) {
				switch (event) {
					case DISMISS_EVENT_TIMEOUT:
					case DISMISS_EVENT_CONSECUTIVE:
					case DISMISS_EVENT_MANUAL:
					case DISMISS_EVENT_SWIPE:
						onCompleted.call();
						break;
					case DISMISS_EVENT_ACTION:
					default:
						break;
				}
			}
		};
		snackbar.setCallback(onSnackbarCompleted);
	}
}
