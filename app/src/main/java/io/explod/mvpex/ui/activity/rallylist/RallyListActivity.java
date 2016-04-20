package io.explod.mvpex.ui.activity.rallylist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.explod.mvpex.R;
import io.explod.mvpex.model.Rally;
import io.explod.mvpex.ui.activity.BaseActivity;
import io.explod.mvpex.ui.view.rally.RallyListAdapter;

import static io.explod.mvpex.util.TagUtils.makeTag;

public class RallyListActivity extends BaseActivity implements RallyListView {

	private static final String TAG = makeTag(RallyListActivity.class);

	private RallyListPresenter presenter;

	@Bind(R.id.recycler_rallies)
	RecyclerView mRalliesRecycler;

	@Bind(R.id.progress_loading_rallies)
	ProgressBar mLoadingRalliesProgress;

	RallyListAdapter mRallyListAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rally_list);

		ButterKnife.bind(this);

		presenter = new RallyListPresenter();
		presenter.attach(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		presenter.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.attach(null);
	}

	@Override
	public void showLoadingRalliesProgressBar() {
		mLoadingRalliesProgress.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoadingRalliesProgressBar() {
		mLoadingRalliesProgress.setVisibility(View.GONE);
	}

	@Override
	public void loadRalliesIntoRecyclerView(@NonNull List<Rally> rallies) {
		if (mRallyListAdapter == null) {
			mRallyListAdapter = new RallyListAdapter();
			mRalliesRecycler.setAdapter(mRallyListAdapter);
		}
		mRallyListAdapter.setRallies(rallies);
	}

	@Override
	public void showRalliesNetworkError() {
		showSnackbarMessage(getText(R.string.error_network));
	}
}
