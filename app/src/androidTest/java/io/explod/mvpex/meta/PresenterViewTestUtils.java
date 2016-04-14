package io.explod.mvpex.meta;

import io.explod.mvpex.util.presenter.PresenterView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PresenterViewTestUtils {

	public static <T extends PresenterView> T mockActiveView(Class<T> viewClass) {
		T view = mock(viewClass);
		when(view.isActivelyReceivingCallbacks()).thenReturn(true);
		return view;
	}
}
