package io.explod.mvpex.ui.activity.rallydetail;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.explod.mvpex.R;
import io.explod.mvpex.ui.activity.BaseActivity;

public class RallyDetailActivity extends BaseActivity implements RallyDetailView {

	public static void launch(@NonNull Context context, long rallyId) {
		Intent intent = new Intent(context, RallyDetailActivity.class);
		intent.putExtra(EXTRA_RALLY_ID, rallyId);
		context.startActivity(intent);
	}

	private static final String EXTRA_RALLY_ID = "rallyId";

	private RallyDetailPresenter mPresenter;

	@Bind(R.id.text_title)
	TextView mTitleText;

	@Bind(R.id.text_location)
	TextView mLocationText;

	@Bind(R.id.text_time)
	TextView mTimeText;

	@Bind(R.id.text_participants)
	TextView mParticipantsText;

	@Bind(R.id.progress_loading_rally)
	ProgressBar mLoadingRallyProgress;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rally_detail);

		ButterKnife.bind(this);

		Bundle extras = getIntent().getExtras();
		long rallyId = extras.getLong(EXTRA_RALLY_ID);

		mPresenter = new RallyDetailPresenter(rallyId);
		mPresenter.attach(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mPresenter.onStart();
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

	@Override
	public void setNumParticipants(int numParticipants) {
		Resources res = getResources();
		CharSequence participantsText = res.getQuantityString(R.plurals.num_participants, numParticipants, numParticipants);
		mParticipantsText.setText(participantsText);
	}

	@Override
	public void showLoadingProgressBar() {
		mLoadingRallyProgress.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoadingProgressBar() {
		mLoadingRallyProgress.setVisibility(View.GONE);
	}

	@Override
	public void showNetworkErrorSnackbar() {
		showSnackbarMessage(getText(R.string.error_network));
	}
}
