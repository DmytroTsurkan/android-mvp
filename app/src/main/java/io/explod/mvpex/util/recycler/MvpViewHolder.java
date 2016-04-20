package io.explod.mvpex.util.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.explod.mvpex.util.presenter.Presenter;
import io.explod.mvpex.util.presenter.PresenterView;

public abstract class MvpViewHolder<P extends Presenter<V>, V extends PresenterView> extends RecyclerView.ViewHolder {

	protected P presenter;

	public <T extends View & PresenterView> MvpViewHolder(T itemView) {
		super(itemView);
	}

	@NonNull
	protected abstract V getView();

	public void bindPresenter(@NonNull P presenter) {
		this.presenter = presenter;
		V view = getView();
		presenter.attach(view);
	}

	public void unbindPresenter() {
		if (presenter != null) {
			presenter.attach(null);
		}
		presenter = null;
	}

}
