package io.explod.mvpex.util;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	@NonNull
	public static CharSequence formatDateTime(@NonNull Date date) {
		DateFormat format = SimpleDateFormat.getDateTimeInstance();
		return format.format(date);
	}
}
