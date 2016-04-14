package io.explod.mvpex.ui.main;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.explod.mvpex.meta.MVPXAndroidTestCase;

import static io.explod.mvpex.meta.PresenterViewTestUtils.mockActiveView;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainPresenterTest extends MVPXAndroidTestCase {

	@Test
	public void testOnLoginSuccessful() throws Exception {
		// view
		MainView view = mockActiveView(MainView.class);
		when(view.getUsername()).thenReturn("mockuser");
		when(view.getPassword()).thenReturn("mockpass");
		// presenter
		MainPresenter presenter = new MainPresenter();
		presenter.useView(view);

		presenter.doLogin();

		verify(view).getPassword();
		verify(view).getUsername();
		verify(view).closeKeyboard();
		verify(view).showLoggingInProgressBar();
		verify(view).hideLoggingInProgressBar();
		verify(view).showLoginOkSnackbar();
	}

	@Test
	public void testLoginFailed() throws Exception {
		// view
		MainView view = mockActiveView(MainView.class);
		when(view.getUsername()).thenReturn("mockuser");
		when(view.getPassword()).thenReturn("mockpass");
		// presenter
		MainPresenter presenter = new MainPresenter();
		presenter.useView(view);

		fail("not adequately tested");
	}

}