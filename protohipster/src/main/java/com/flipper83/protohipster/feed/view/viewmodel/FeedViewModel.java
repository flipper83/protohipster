package com.flipper83.protohipster.feed.view.viewmodel;

import com.flipper83.protohipster.feed.domain.boundaries.FeedBoundary;
import com.flipper83.protohipster.feed.domain.boundaries.HipsterBoundary;
import com.flipper83.protohipster.feed.domain.interactors.GetFeed;
import com.flipper83.protohipster.feed.domain.interactors.GetMyLikers;
import com.flipper83.protohipster.feed.domain.interactors.LikeHipster;
import com.flipper83.protohipster.feed.view.viewmodel.mapper.HipsterViewMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.util.functions.Func1;
import rx.util.functions.Func2;

/**
 * A list of view information Hipsters
 */
public class FeedViewModel {

    HipsterViewMapper hipsterViewMapper;
    GetFeed getFeed;
    GetMyLikers getMyLikers;
    LikeHipster likeUser;

    private List<HipsterViewModel> hipsters = new ArrayList<HipsterViewModel>();

    public FeedViewModel(GetFeed getFeed,GetMyLikers getMyLikers,LikeHipster likeUser, HipsterViewMapper mapper) {
        this.getFeed = getFeed;
        this.getMyLikers = getMyLikers;
        this.likeUser = likeUser;
        this.hipsterViewMapper = mapper;
    }

    public void addHipster(HipsterViewModel hipster) {
        hipsters.add(hipster);
    }

    public List<HipsterViewModel> getHipsters() {
        return Collections.unmodifiableList(hipsters);
    }

    public Observable<List<HipsterViewModel>> populateFeed() {
        return Observable.zip(getFeed.getFeed(), getMyLikers.getMyLikers(), new Func2<FeedBoundary, List<String>, List<HipsterViewModel>>() {
            @Override
            public List<HipsterViewModel> call(FeedBoundary feedBoundary, List<String> myLikers) {
                hipsters.clear();

                for (HipsterBoundary hipsterBoundary : feedBoundary.getHipsters()) {

                    HipsterViewModel hipsterViewModel = hipsterViewMapper.mapper(hipsterBoundary);

                    boolean likedByMe = myLikers.contains(hipsterViewModel.getUserId());
                    hipsterViewModel.setLikedByMe(likedByMe);

                    hipsters.add(hipsterViewModel);
                }

                return hipsters;
            }
        });
    }

    public Observable<HipsterViewModel> likeHipster(int position){
        HipsterViewModel hipster = hipsters.get(position);
        hipster.setLikedByMe(true);

        return likeUser.like(hipster.getUserId()).map( new Func1<String, HipsterViewModel>() {
            @Override
            public HipsterViewModel call(String userId) {
                for (HipsterViewModel hipsterViewModel : hipsters) {
                    if (hipsterViewModel.getUserId().equals(userId)){
                        hipsterViewModel.setRating(hipsterViewModel.getRating() + 1);
                        hipsterViewModel.setLikedByMe(true);
                        return hipsterViewModel;
                    }
                }

                return HipsterViewModel.EMPTY;
            }
        });

    }



}

