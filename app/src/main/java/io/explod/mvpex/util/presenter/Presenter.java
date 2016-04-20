package io.explod.mvpex.util.presenter;

import android.support.annotation.Nullable;

/**
 * Base presenter class
 *
 * @param <T> PresenterView that should receive callbacks
 */
public abstract class Presenter<T extends PresenterView> {

	@Nullable
	private T view;

	/**
	 * @return the current view assigned to this presenter
	 */
	@Nullable
	protected T getView() {
		return view;
	}

	/**
	 * Returns the current view as long at it is receiving callbacks.
	 */
	@Nullable
	protected T getActiveView() {
		T view = getView();
		if (view != null && view.isActivelyReceivingCallbacks()) {
			return view;
		}
		return null;
	}

	/**
	 * Assign a view to receive callbacks.
	 * Assign null when this presenter should no longer emit callbacks.
	 */
	public void attach(@Nullable T view) {
		this.view = view;
	}

}
