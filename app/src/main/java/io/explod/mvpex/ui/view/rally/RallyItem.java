package io.explod.mvpex.ui.view.rally;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import io.explod.mvpex.R;

public class RallyItem extends LinearLayout implements RallyItemView {

	@Bind(R.id.text_title)
	TextView mTitleText;

	@Bind(R.id.text_location)
	TextView mLocationText;

	@Bind(R.id.text_time)
	TextView mTimeText;

	public RallyItem(@NonNull Context context) {
		super(context);
		init(context);
	}

	private void init(@NonNull Context context) {
		LayoutInflater.from(context).inflate(R.layout.item_rally, this);
		setOrientation(VERTICAL);
	}

	@Override
	public boolean isActivelyReceivingCallbacks() {
		return true;
	}

	@Override
	public void setTitle(@NonNull CharSequence title) {
		mTitleText.setText(title);
	}

	@Override
	public void setLocation(@NonNull CharSequence location) {
		mLocationText.setText(location);
	}

	@Override
	public void setTime(@NonNull CharSequence date) {
		mTimeText.setText(date);
	}

}
