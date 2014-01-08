package com.flipper83.protohipster.feed.view.provider;

import com.flipper83.protohipster.feed.view.viewmodel.FeedViewModel;

/**
 *
 */
public class FeedProvider {

    FeedViewModel viewModel;

    public FeedProvider(FeedViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public FeedViewModel getFeed() {
        return viewModel;
    }

}
