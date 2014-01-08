package com.flipper83.protohipster.feed.view.module;


import android.content.Context;

import com.flipper83.protohipster.daggerUtils.ForApplication;
import com.flipper83.protohipster.feed.domain.interactors.GetFeed;
import com.flipper83.protohipster.feed.domain.interactors.GetMyLikers;
import com.flipper83.protohipster.feed.domain.interactors.LikeHipster;
import com.flipper83.protohipster.feed.view.viewmodel.FeedViewModel;
import com.flipper83.protohipster.feed.view.viewmodel.mapper.HipsterViewMapper;
import com.flipper83.protohipster.feed.view.viewmodel.mapper.HipsterViewMapperManual;
import com.flipper83.protohipster.feed.view.provider.FeedProvider;
import com.flipper83.protohipster.uibase.transformation.TransformationBuilder;
import com.flipper83.protohipster.uibase.transformation.privates.TransformationBuilderPicasso;
import com.flipper83.protohipster.feed.view.ui.HipsterListFragment;
import com.flipper83.protohipster.feed.view.ui.ProtoActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(injects = {ProtoActivity.class, HipsterListFragment.class},
        complete = false,
        library = true)
public class FeedViewModule {

    @Provides
    FeedProvider provideFeedProvider(FeedViewModel viewModel) {
        return new FeedProvider(viewModel);
    }

    @Provides
    FeedViewModel provideFeedViewModel(GetFeed getFeed,GetMyLikers getMyLikers,
                                       LikeHipster likeHipster, HipsterViewMapper mapper) {
        return new FeedViewModel(getFeed,getMyLikers,likeHipster,mapper);
    }

    @Provides
    @Singleton
    Picasso providePicasso(@ForApplication Context context) {
        return Picasso.with(context);
    }

    @Provides
    TransformationBuilder provideTransformationBuilder(TransformationBuilderPicasso
                                                               transformationBuilderPicasso) {
        return transformationBuilderPicasso;
    }

    @Provides
    HipsterViewMapper provideHipsterViewMapperManual(HipsterViewMapperManual hipsterViewMapperManual) {
        return hipsterViewMapperManual;
    }
}
