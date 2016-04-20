package io.explod.mvpex.ui.view.rally;

import android.support.annotation.NonNull;
import android.view.View;

import butterknife.ButterKnife;
import io.explod.mvpex.util.recycler.MvpViewHolder;

/* default */ class RallyItemViewHolder extends MvpViewHolder<RallyItemPresenter, RallyItemView> {

	public <T extends View & RallyItemView> RallyItemViewHolder(@NonNull T itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	@NonNull
	@Override
	protected RallyItemView getView() {
		return (RallyItemView) itemView;
	}
}
