package io.explod.mvpex.ui.rallylist;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;

import java.util.List;

import io.explod.mvpex.meta.MVPXAndroidTestCase;
import io.explod.mvpex.model.Rally;

import static io.explod.mvpex.meta.PresenterViewTestUtils.mockActiveView;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class RallyListPresenterTest extends MVPXAndroidTestCase {

	@Test
	public void testOnStart() throws Exception {
		// view
		RallyListView view = mockActiveView(RallyListView.class);
		// presenter
		RallyListPresenter presenter = new RallyListPresenter();
		presenter.useView(view);

		presenter.onStart();

		verify(view).showLoadingRalliesProgressBar();
		verify(view).hideLoadingRalliesProgressBar();
		verify(view).loadRalliesIntoRecyclerView(Matchers.<List<Rally>>any());
	}

	@Test
	public void testOnLoginFailed() throws Exception {
		// view
		RallyListView view = mockActiveView(RallyListView.class);
		// presenter
		RallyListPresenter presenter = new RallyListPresenter();
		presenter.useView(view);

		fail("not adequately tested");
	}

}