package io.explod.mvpex.ui.main;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.explod.mvpex.meta.MVPAndroidTestCase;
import io.explod.mvpex.model.User;

import static io.explod.mvpex.meta.PresenterViewTestUtils.mockActiveView;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainPresenterTest extends MVPAndroidTestCase {

	@Test
	public void testOnLoginSuccessful() throws Exception {
		// view
		MainView view = mockActiveView(MainView.class);
		when(view.getPassword()).thenReturn("mockuser");
		when(view.getPassword()).thenReturn("mockpass");
		// presenter
		MainPresenter presenter = new MainPresenter();
		presenter.useView(view);

		presenter.doLogin();

		verify(view).loggingIn();
		verify(view).onLoginSuccessful(any(User.class));
	}

	@Test
	public void testOnBadLogin() throws Exception {
		// view
		MainView view = mockActiveView(MainView.class);
		// presenter
		MainPresenter presenter = new MainPresenter();
		presenter.useView(view);

		presenter.onBadLogin();

		verify(view).onBadLogin();
	}

	@Test
	public void testOnLoginFailed() throws Exception {
		// view
		MainView view = mockActiveView(MainView.class);
		// presenter
		MainPresenter presenter = new MainPresenter();
		presenter.useView(view);

		Exception ex = new Exception("failed");
		presenter.onLoginError(ex);

		verify(view).onLoginError(ex);
	}
}