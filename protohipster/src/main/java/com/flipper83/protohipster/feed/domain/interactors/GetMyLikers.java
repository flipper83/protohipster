package com.flipper83.protohipster.feed.domain.interactors;

import java.util.List;

import rx.Observable;

/**
 * This interface define the contract for obtain the feed.
 */
public interface GetMyLikers {
    Observable<List<String>> getMyLikers();
}
