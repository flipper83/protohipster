package com.flipper83.protohipster.feed.datasource.module;

import android.util.Log;

import com.flipper83.protohipster.feed.datasource.api.Api;
import com.flipper83.protohipster.feed.datasource.api.call.rest.GetFeedCall;
import com.flipper83.protohipster.feed.datasource.api.callback.ApiResponseCallback;
import com.flipper83.protohipster.feed.datasource.api.model.UserApi;
import com.flipper83.protohipster.feed.datasource.api.model.UserApiEntry;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetUserCallback;
import com.flipper83.protohipster.feed.datasource.interfaces.UserDataSource;
import com.flipper83.protohipster.feed.domain.gateway.Hipster;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;


/**
 * implementation for UserDataSource
 */
class UserDataSourceImp implements UserDataSource, ApiResponseCallback<List<UserApiEntry>> {


    @Inject
    @Named("ApiRest")
    Api api;

    private Collection<GetUserCallback> getUserCallbacks =
            Collections.synchronizedCollection(new ArrayList<GetUserCallback>());

    UserDataSourceImp(Api api) {
        this.api = api;
    }

    @Override
    public void getUsers(GetUserCallback callback) {
        getUserCallbacks.add(callback);
        api.call(new GetFeedCall(this));
    }

    @Override
    public void complete(List<UserApiEntry> response) {
        List<Hipster> hipsters = new ArrayList<Hipster>();

        for (UserApiEntry userApiEntry : response) {
            UserApi user = userApiEntry.getUser();

            Log.d("borrar",user.getEmail());
            //for this test we use the email as id
            Hipster hipster = new Hipster(user.getEmail());
            hipster.setName(user.getName().getFirst());
            hipster.setSurname(user.getName().getLast());
            hipster.setAvatar(user.getPicture().getThumbnail());

            hipsters.add(hipster);
        }

        synchronized (getUserCallbacks){
            for (GetUserCallback getUserCallback : getUserCallbacks) {
                getUserCallback.usersReady(hipsters);
            }

            getUserCallbacks.clear();
        }
    }
}
