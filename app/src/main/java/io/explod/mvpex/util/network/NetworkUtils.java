package io.explod.mvpex.util.network;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.net.SocketTimeoutException;

public class NetworkUtils {

	@VisibleForTesting
	@NonNull
	public static Throwable getNetworkError() {
		return new SocketTimeoutException("testing bad network");
	}

	public static boolean isNetworkError(@NonNull Throwable t) {
		return true;
	}
}
