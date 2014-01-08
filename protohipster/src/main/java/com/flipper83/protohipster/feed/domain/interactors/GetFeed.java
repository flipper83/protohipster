package com.flipper83.protohipster.feed.domain.interactors;

import com.flipper83.protohipster.feed.domain.boundaries.FeedBoundary;

import rx.Observable;

/**
 * This interface define the contract for obtain the feed.
 */
public interface GetFeed {
    Observable<FeedBoundary> getFeed();
}
