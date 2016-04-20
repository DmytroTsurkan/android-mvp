package io.explod.mvpex.ui.view.rally;

import android.support.annotation.NonNull;

import io.explod.mvpex.util.presenter.PresenterView;

public interface RallyItemView extends PresenterView {

	void setTitle(@NonNull CharSequence title);

	void setLocation(@NonNull CharSequence location);

	void setTime(@NonNull CharSequence date);

}
