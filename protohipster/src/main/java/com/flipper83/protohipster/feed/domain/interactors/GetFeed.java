package com.flipper83.protohipster.feed.domain.interactors;

import com.flipper83.protohipster.feed.view.viewmodel.callback.GetFeedCallback;

/**
 * This interface define the contract for obtain the feed.
 */
public interface GetFeed {
    void getFeed(GetFeedCallback getFeedCallback);
}
