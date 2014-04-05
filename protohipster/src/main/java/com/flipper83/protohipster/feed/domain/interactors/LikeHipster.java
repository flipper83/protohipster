package com.flipper83.protohipster.feed.domain.interactors;

import com.flipper83.protohipster.feed.view.viewmodel.callback.LikeHipsterCallback;

/**
 * This interface define the contract for like a hipster
 */
public interface LikeHipster {
    public void like(String userId, LikeHipsterCallback likeHipsterCallback);
}
