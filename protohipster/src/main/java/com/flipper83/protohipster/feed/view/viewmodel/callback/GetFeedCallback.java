package com.flipper83.protohipster.feed.view.viewmodel.callback;

import com.flipper83.protohipster.feed.domain.boundaries.FeedBoundary;

/**
 *
 */
public interface GetFeedCallback {
    void feedReady(FeedBoundary feedBoundary);
}
