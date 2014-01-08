package com.flipper83.protohipster.uibase.app;

import android.app.Application;

import com.flipper83.protohipster.feed.datasource.module.DataSourceModule;
import com.flipper83.protohipster.feed.domain.module.DomainModule;
import com.flipper83.protohipster.globalutils.module.GlobalModule;
import com.flipper83.protohipster.globalutils.module.AndroidModule;
import com.flipper83.protohipster.feed.view.module.FeedViewModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Application called on Android Manifest
 */
public class ProtoApplication extends Application{
    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();

        graph = ObjectGraph.create(getModules().toArray());
        graph.injectStatics();
    }

    protected List<Object> getModules() {
        return Arrays.asList(new AndroidModule(this),new GlobalModule(), new DomainModule(),new FeedViewModule(),new DataSourceModule());
    }



    public void inject(Object object) {
        graph.inject(object);
    }

}
