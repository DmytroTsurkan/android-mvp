package io.explod.mvpex.ui.activity.rallydetail;

import android.support.annotation.NonNull;

import io.explod.mvpex.util.presenter.PresenterView;

public interface RallyDetailView extends PresenterView {

	void showLoadingProgressBar();

	void hideLoadingProgressBar();

	void setTitle(@NonNull CharSequence title);

	void setLocation(@NonNull CharSequence location);

	void setTime(@NonNull CharSequence date);

	void setNumParticipants(int numParticipants);

	void showNetworkErrorSnackbar();
}
