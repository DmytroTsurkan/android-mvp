package io.explod.mvpex.module;


import javax.inject.Singleton;

import dagger.Component;
import io.explod.mvpex.module.modules.AppModule;
import io.explod.mvpex.module.modules.GsonModule;
import io.explod.mvpex.module.modules.MockModule;
import io.explod.mvpex.module.modules.service.AppAuthorizedServiceModule;
import io.explod.mvpex.module.modules.service.AppServiceModule;
import io.explod.mvpex.module.modules.service.DevelopingServiceModule;
import io.explod.mvpex.module.modules.service.MockServiceModule;
import io.explod.mvpex.module.modules.service.NetworkModule;

@Component(
	modules = {
		AppAuthorizedServiceModule.class,
		AppServiceModule.class,
		DevelopingServiceModule.class,
		MockServiceModule.class,
		AppModule.class,
		GsonModule.class,
		NetworkModule.class,
		MockModule.class,
	}
)
@Singleton
public interface ObjectGraph extends Injections {
}
