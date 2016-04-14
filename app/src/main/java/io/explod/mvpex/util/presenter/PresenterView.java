package io.explod.mvpex.util.presenter;

public interface PresenterView {

	/**
	 * Call to determine whether or not this view is currently receiving callbacks.
	 * An example of a PresenterView that would not want to receive callback is an Activity that is
	 * finishing or destroyed.
	 */
	boolean isActivelyReceivingCallbacks();

}
