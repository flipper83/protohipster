package com.flipper83.protohipster.feed.view.viewmodel;

/**
 * An User View Model
 */
public class HipsterViewModel {
    public static final HipsterViewModel EMPTY = new HipsterViewModel();

    private String userId = "";
    private String name = "";
    private String urlAvatar = "";
    private int rating;
    private boolean likedByMe;

    public HipsterViewModel() {

    }

    public String getName() {
        return name;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public int getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLikedByMe(boolean likedByMe) {
        this.likedByMe = likedByMe;
    }

    public boolean isLikedByMe() {
        return likedByMe;
    }
}
