package io.explod.mvpex.util.presenter;

/**
 * PresenterView interface.
 * Methods are extracted into an interface for testing purposes.
 * <p>
 * By convention:
 * <p>
 * - data methods start with "get"
 * <p>
 * - callback methods start with "on"
 */
public interface PresenterView {

	/**
	 * Call to determine whether or not this view is currently receiving callbacks.
	 * An example of a PresenterView that would not want to receive callback is an Activity that is
	 * finishing or destroyed.
	 * <p>
	 * Any calls to "onXXX" methods should first check to see if the
	 * PresenterView is ok to handle them with this method.
	 */
	boolean isActivelyReceivingCallbacks();

}
