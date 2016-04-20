package io.explod.mvpex.network.service;

import io.explod.mvpex.model.Items;
import io.explod.mvpex.model.Rally;
import io.explod.mvpex.model.User;
import io.explod.mvpex.model.request.LoginRequest;
import retrofit2.http.Body;
import rx.Single;

public interface DevelopingService {

	Single<User> login(@Body LoginRequest request);

	Single<Items<Rally>> getRallies();

	Single<Rally> getRally(long rallyId);

	Single<Integer> getNumParticipants(long rallyId);

}
