package com.flipper83.protohipster.feed.domain.interactors;

import rx.Observable;

/**
 * This interface define the contract for like a hipster
 */
public interface LikeHipster {
    public Observable<String> like(String userId);
}
