package com.flipper83.protohipster.feed.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.flipper83.protohipster.R;
import com.flipper83.protohipster.feed.view.provider.FeedProvider;
import com.flipper83.protohipster.feed.view.ui.callback.RefreshFeedCallback;
import com.flipper83.protohipster.feed.view.viewmodel.FeedViewModel;
import com.flipper83.protohipster.feed.view.viewmodel.HipsterViewModel;
import com.flipper83.protohipster.uibase.base.BaseFragment;
import com.flipper83.protohipster.uibase.transformation.TransformationBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

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
                feed.likeHipster(pos, populateFeedCallback);

                refreshData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        feed = feedProvider.getFeed();

        refreshData();

        feed.populateFeed(populateFeedCallback);


    }

    @Override
    public void onPause() {
        super.onPause();
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

    private RefreshFeedCallback populateFeedCallback = new RefreshFeedCallback() {
        @Override
        public void refreshPopulated() {
            refreshData();
        }
    };


}
