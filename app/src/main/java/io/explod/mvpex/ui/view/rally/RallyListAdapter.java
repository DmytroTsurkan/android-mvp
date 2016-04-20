package io.explod.mvpex.ui.view.rally;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import java.util.List;

import io.explod.mvpex.model.Rally;
import io.explod.mvpex.util.recycler.MvpRecyclerAdapter;

public class RallyListAdapter extends MvpRecyclerAdapter<RallyItemPresenter, RallyItemView, RallyItemViewHolder> {

	@Nullable
	private List<Rally> mRallies;

	public void setRallies(@Nullable List<Rally> rallies) {
		mRallies = rallies;
		notifyDataSetChanged();
	}

	@Nullable
	private Rally getItem(int position) {
		return mRallies == null ? null : mRallies.get(position);
	}

	@Override
	protected long getStableId(int position) {
		Rally rally = getItem(position);
		return rally == null ? -1 : rally.id;
	}

	@NonNull
	@Override
	protected RallyItemPresenter createPresenter(int position) {
		Rally rally = getItem(position);
		return new RallyItemPresenter(rally);
	}

	@Override
	public RallyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		RallyItem item = new RallyItem(parent.getContext());
		return new RallyItemViewHolder(item);
	}

	@Override
	public int getItemCount() {
		return mRallies == null ? 0 : mRallies.size();
	}
}
