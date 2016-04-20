package io.explod.mvpex.ui.view.rally;

import org.junit.Test;

import io.explod.mvpex.model.Rally;

import static io.explod.mvpex.meta.PresenterViewTestUtils.mockActiveView;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class RallyItemPresenterTest {

	@Test
	public void testUseView() throws Exception {
		// view
		RallyItemView view = mockActiveView(RallyItemView.class);
		// model
		Rally rally = Rally.mock();
		// presenter
		RallyItemPresenter presenter = new RallyItemPresenter(rally);

		presenter.attach(view);

		verify(view).setTitle(eq(rally.name));
		verify(view).setLocation(eq(rally.location));
		verify(view).setLocation(any(String.class));
	}
}