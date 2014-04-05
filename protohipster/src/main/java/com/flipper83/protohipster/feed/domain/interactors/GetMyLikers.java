package com.flipper83.protohipster.feed.domain.interactors;

import com.flipper83.protohipster.feed.view.viewmodel.callback.GetMyLikersUiCallback;

/**
 * This interface define the contract for obtain the feed.
 */
public interface GetMyLikers {
    void getMyLikers(GetMyLikersUiCallback getMyLikersUiCallback);
}
