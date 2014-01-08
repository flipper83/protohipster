package com.flipper83.protohipster.feed.datasource.module;

import android.content.Context;

import com.flipper83.protohipster.daggerUtils.ForApplication;
import com.flipper83.protohipster.feed.datasource.api.Api;
import com.flipper83.protohipster.feed.datasource.interfaces.LikeDataSource;
import com.flipper83.protohipster.feed.datasource.interfaces.UserDataSource;
import com.flipper83.protohipster.feed.datasource.module.api.ApiParse;
import com.flipper83.protohipster.feed.datasource.module.api.ApiRest;
import com.flipper83.protohipster.globalutils.cache.Cache;
import com.flipper83.protohipster.globalutils.cache.implementations.MapKeysCache;
import com.flipper83.protohipster.globalutils.module.AndroidModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(library = true,
        includes = AndroidModule.class)
public class DataSourceModule {

    public DataSourceModule() {

    }

    @Provides
    @Singleton
    @Named("ApiRest")
    Api provideApiRest(ApiRest apiRest) {
        return apiRest;
    }

    @Provides
    @Singleton
    @Named("ApiParse")
    Api provideApiParse(@ForApplication Context context) {
        return new ApiParse(context);
    }


    @Provides
    @Singleton
    @Named("MapKeysCache")
    Cache provideApiParse() {
        return new MapKeysCache();
    }

    @Provides
    RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                .setServer(ApiRest.DOMAIN)
                .build();
    }

    @Provides
    UserDataSource provideUserDataSource(@Named("ApiRest") Api api) {
        return new UserDataSourceImp(api);
    }

    @Provides
    LikeDataSource provideLikeDataSource(LikeDataSourceParse likeDataSource) {
        return likeDataSource;
    }



}
