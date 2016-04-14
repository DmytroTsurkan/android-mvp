package rx.plugins;  // here be dragons

public class RxJavaTestPlugins extends RxJavaPlugins {

	RxJavaTestPlugins() {
		super();
	}

	public static void resetPlugins() {
		// need to make reset public to hack in our scheduler overrides
		getInstance().reset();
	}
}