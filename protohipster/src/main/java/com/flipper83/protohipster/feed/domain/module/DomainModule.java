package com.flipper83.protohipster.feed.domain.module;

import com.flipper83.protohipster.feed.domain.mappers.HipsterMapper;
import com.flipper83.protohipster.feed.domain.mappers.HipsterMapperManual;
import com.flipper83.protohipster.feed.domain.interactors.GetFeed;
import com.flipper83.protohipster.feed.domain.interactors.GetMyLikers;
import com.flipper83.protohipster.feed.domain.interactors.LikeHipster;
import com.flipper83.protohipster.globalutils.rating.RatingCalculator;
import com.flipper83.protohipster.globalutils.rating.RatingCalculatorFiveStars;

import dagger.Module;
import dagger.Provides;

@Module(complete = false,
        library = true)
public class DomainModule {

    @Provides
    GetFeed provideFeed(GetFeedImpl feedImp) {
        return feedImp;
    }

    @Provides
    GetMyLikers provideGetMyLikers(GetMyLikersImpl getMyLikersImp){
        return getMyLikersImp;
    }

    @Provides
    LikeHipster provideLikeHipster(LikeHipsterImp likeHipsterImp){
        return likeHipsterImp;
    }

    @Provides
    RatingCalculator provideRatingFiveStars(RatingCalculatorFiveStars ratingCalculatorFiveStars) {
        return ratingCalculatorFiveStars;
    }

    @Provides
    HipsterMapper provideHipsterMapperManual(HipsterMapperManual hipsterMapperAuto) {
        return hipsterMapperAuto;
    }

}
