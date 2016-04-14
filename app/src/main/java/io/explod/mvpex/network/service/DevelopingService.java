package io.explod.mvpex.network.service;

import io.explod.mvpex.model.User;
import io.explod.mvpex.model.request.LoginRequest;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.Body;
import rx.Single;

public interface DevelopingService {

	Single<Result<User>> login(@Body LoginRequest request);
	
}
