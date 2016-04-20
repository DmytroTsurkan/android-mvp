package io.explod.mvpex.ui.activity.rallylist;

import android.support.annotation.NonNull;

import java.util.List;

import io.explod.mvpex.model.Rally;
import io.explod.mvpex.util.presenter.PresenterView;

/* default */ interface RallyListView extends PresenterView {

	void showLoadingRalliesProgressBar();

	void hideLoadingRalliesProgressBar();

	void loadRalliesIntoRecyclerView(@NonNull List<Rally> rallies);

	void showRalliesNetworkError();

	void launchRallyActivity(long id);
}
