package io.explod.mvpex.ui.activity.rallylist;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.explod.mvpex.meta.MVPXAndroidTestCase;

import static io.explod.mvpex.meta.PresenterViewTestUtils.mockActiveView;

@RunWith(AndroidJUnit4.class)
public class RallyListPresenterTest extends MVPXAndroidTestCase {

	@Test
	public void testOnStart() throws Exception {
		// view
		RallyListView view = mockActiveView(RallyListView.class);
		// presenter
		RallyListPresenter presenter = new RallyListPresenter();
		presenter.attach(view);

		// fixme: blocks forever
		//presenter.onStart();

//		verify(view).showLoadingRalliesProgressBar();
//		verify(view).hideLoadingRalliesProgressBar();
//		verify(view).loadRalliesIntoRecyclerView(Matchers.<List<Rally>>any());
	}

	@Test
	public void testOnLoginFailed() throws Exception {
		// view
		RallyListView view = mockActiveView(RallyListView.class);
		// presenter
		RallyListPresenter presenter = new RallyListPresenter();
		presenter.attach(view);

//		fail("not adequately tested");
	}

}