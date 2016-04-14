package io.explod.mvpex.module;

import io.explod.mvpex.ui.rallylist.RallyListPresenter;
import io.explod.mvpex.ui.main.MainPresenter;

public interface Injections {

	void inject(MainPresenter target);

	void inject(RallyListPresenter target);
}
