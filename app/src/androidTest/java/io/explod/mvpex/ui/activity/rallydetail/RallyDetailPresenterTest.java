package io.explod.mvpex.ui.activity.rallydetail;

import org.junit.Test;

import static io.explod.mvpex.meta.PresenterViewTestUtils.mockActiveView;

public class RallyDetailPresenterTest {

	@Test
	public void testOnStart() throws Exception {
		// view
		RallyDetailView view = mockActiveView(RallyDetailView.class);
		// presenter
		RallyDetailPresenter presenter = new RallyDetailPresenter(1);
		presenter.attach(view);

		// fixme: blocks forever
		//presenter.onStart();

//		verify(view).showLoadingProgressBar();
//		verify(view).hideLoadingProgressBar();
//		verify(view).setTitle(any(CharSequence.class));
//		verify(view).setTime(any(CharSequence.class));
//		verify(view).setLocation(any(CharSequence.class));
	}

	@Test
	public void testOnLoginFailed() throws Exception {
		// view
		RallyDetailView view = mockActiveView(RallyDetailView.class);
		// presenter
		RallyDetailPresenter presenter = new RallyDetailPresenter(1);
		presenter.attach(view);

//		fail("not adequately tested");
	}

}