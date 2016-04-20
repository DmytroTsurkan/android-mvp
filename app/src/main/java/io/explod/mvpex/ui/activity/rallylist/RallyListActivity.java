package io.explod.mvpex.ui.activity.rallylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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

public class RallyListActivity extends BaseActivity implements RallyListView {

	public static void launch(@NonNull Context context) {
		Intent intent = new Intent(context, RallyListActivity.class);
		context.startActivity(intent);
	}

	private RallyListPresenter mPresenter;

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

		mRalliesRecycler.setLayoutManager(new LinearLayoutManager(this));

		mPresenter = new RallyListPresenter();
		mPresenter.attach(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mPresenter.onStart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPresenter.attach(null);
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
