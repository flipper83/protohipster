package com.flipper83.protohipster.feed.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.flipper83.protohipster.R;
import com.flipper83.protohipster.feed.view.provider.FeedProvider;
import com.flipper83.protohipster.feed.view.viewmodel.FeedViewModel;
import com.flipper83.protohipster.feed.view.viewmodel.HipsterViewModel;
import com.flipper83.protohipster.uibase.base.BaseFragment;
import com.flipper83.protohipster.uibase.transformation.TransformationBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.concurrency.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A placeholder fragment containing a simple view.
 */
public class HipsterListFragment extends BaseFragment {


    @Inject
    FeedProvider feedProvider;

    @Inject
    Picasso picasso;
    @Inject
    TransformationBuilder transformationBuilder;
    ListView listviewFeed;
    private FeedViewModel feed;
    private FeedAdapter feedAdapter;
    private Subscription subscriptionFeed;
    private Subscription subscriptionLike;

    public HipsterListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_proto, container, false);

        mapGUI(rootView);

        hookActionListeners();

        return rootView;
    }

    private void hookActionListeners() {
        listviewFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                subscriptionLike = feed.likeHipster(pos)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observerLike);

                refreshData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        feed = feedProvider.getFeed();

        refreshData();

        subscriptionFeed = feed.populateFeed()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observerPopulateFeed);


    }

    @Override
    public void onPause() {
        super.onPause();
        if (subscriptionFeed != null) {
            subscriptionFeed.unsubscribe();
        }

        if (subscriptionLike != null) {
            subscriptionLike.unsubscribe();
        }
    }

    private void mapGUI(View rootView) {
        listviewFeed = (ListView) rootView.findViewById(R.id.lv_feed);

    }

    private void refreshData() {
        List<HipsterViewModel> hipsters = feed.getHipsters();

        if (feedAdapter == null) {
            feedAdapter = new FeedAdapter(getActivity(), hipsters, picasso, transformationBuilder);
            listviewFeed.setAdapter(feedAdapter);
        } else {
            feedAdapter.notifyDataSetChanged();
        }
    }

    private Observer<? super HipsterViewModel> observerLike = new Observer<HipsterViewModel>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            //TODO MANAGE ERRORS
        }

        @Override
        public void onNext(HipsterViewModel args) {
            refreshData();
        }
    };

    private Observer<? super List<HipsterViewModel>> observerPopulateFeed =
            new Observer<List<HipsterViewModel>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    //TODO MANAGE ERRORS
                }

                @Override
                public void onNext(List<HipsterViewModel> args) {
                    refreshData();
                }
            };

}
