package io.explod.mvpex.util.recycler;

import android.support.annotation.NonNull;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import io.explod.mvpex.util.presenter.Presenter;
import io.explod.mvpex.util.presenter.PresenterView;

import static io.explod.mvpex.util.TagUtils.makeTag;

public abstract class MvpRecyclerAdapter<P extends Presenter<V>, V extends PresenterView, VH extends MvpViewHolder<P, V>> extends RecyclerView.Adapter<VH> {

	private static final String TAG = makeTag(MvpRecyclerAdapter.class);

	protected static final long NO_ID = -1;

	@NonNull
	// todo: remove items when the dataset changes
	protected final LongSparseArray<P> presenters;

	public MvpRecyclerAdapter() {
		super.setHasStableIds(true);
		presenters = new LongSparseArray<>();
	}

	@NonNull
	protected P getPresenter(int position) {
		long id = getItemId(position);

		if (id == NO_ID) {
			Log.w(TAG, "no id for item at position " + position);
			return createPresenter(position);
		} else {
			return getPresenterForId(id, position);
		}
	}

	@NonNull
	protected P getPresenterForId(long id, int position) {
		P presenter;
		synchronized (presenters) {
			presenter = presenters.get(id);
			if (presenter == null) {
				presenter = createPresenter(position);
				presenters.put(id, presenter);
			}
		}
		return presenters.get(id);
	}

	@Override
	public long getItemId(int position) {
		return getStableId(position);
	}

	protected abstract long getStableId(int position);

	@NonNull
	protected abstract P createPresenter(int position);

	@Override
	public void onViewRecycled(VH holder) {
		super.onViewRecycled(holder);
		holder.unbindPresenter();
	}

	@Override
	public boolean onFailedToRecycleView(VH holder) {
		// Sometimes, if animations are running on the itemView's children, the RecyclerView won't
		// be able to recycle the view. We should still unbind the presenter.
		holder.unbindPresenter();
		return super.onFailedToRecycleView(holder);
	}

	@Override
	public void onBindViewHolder(VH holder, int position) {
		P presenter = getPresenter(position);
		holder.bindPresenter(presenter);
	}

}
