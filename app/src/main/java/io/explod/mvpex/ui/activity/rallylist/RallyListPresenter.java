package io.explod.mvpex.ui.activity.rallylist;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import javax.inject.Inject;

import io.explod.mvpex.model.Rally;
import io.explod.mvpex.network.service.DevelopingService;
import io.explod.mvpex.util.dagger.VisibleForInjection;
import io.explod.mvpex.util.presenter.RxPresenter;
import rx.android.schedulers.AndroidSchedulers;

import static io.explod.mvpex.App.getApp;

@VisibleForInjection
public class RallyListPresenter extends RxPresenter<RallyListView> {

	@Inject
	DevelopingService mDevelopingService;

	public RallyListPresenter() {
		getApp().getObjectGraph().inject(this);
	}

	public void onStart() {
		loadRallies();
	}

	public void onRallyClick(@NonNull Rally rally) {
		RallyListView view = getActiveView();
		if (view == null) return;

		view.launchRallyActivity(rally.id);
	}

	private void loadRallies() {
		RallyListView view = getActiveView();
		if (view == null) return;

		view.showLoadingRalliesProgressBar();

		bind(mDevelopingService.getRallies()
			.observeOn(AndroidSchedulers.mainThread())
			.map(items -> items.collection)
			.subscribe(
				this::onRallies,
				this::onRalliesError
			));
	}

	@VisibleForTesting
	void onRallies(@NonNull List<Rally> rallies) {
		RallyListView view = getActiveView();
		if (view == null) return;

		view.hideLoadingRalliesProgressBar();
		view.loadRalliesIntoRecyclerView(rallies);
	}

	@VisibleForTesting
	void onRalliesError(@NonNull Throwable t) {
		RallyListView view = getActiveView();
		if (view == null) return;

		view.hideLoadingRalliesProgressBar();
		view.showRalliesNetworkError();
	}

}
