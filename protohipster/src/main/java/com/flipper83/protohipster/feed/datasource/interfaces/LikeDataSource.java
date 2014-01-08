package com.flipper83.protohipster.feed.datasource.interfaces;

import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetCountLikesCallback;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.GetMyLikersCallback;
import com.flipper83.protohipster.feed.datasource.interfaces.callbacks.LikeUserCallback;

import java.util.List;

/**
 * This is the data source that provides likes
 */
public interface LikeDataSource {
    void getCountLikesForUser(List<String> userIds, GetCountLikesCallback getCountLikesCallback);
    void getMyLikers(GetMyLikersCallback getLikersForUserCallback);
    void likeUser(String userId, LikeUserCallback likeUserCallback);
}
