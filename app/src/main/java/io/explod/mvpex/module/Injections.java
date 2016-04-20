package io.explod.mvpex.module;

import io.explod.mvpex.ui.activity.rallylist.RallyListPresenter;
import io.explod.mvpex.ui.activity.main.MainPresenter;

public interface Injections {

	void inject(MainPresenter target);

	void inject(RallyListPresenter target);
}
