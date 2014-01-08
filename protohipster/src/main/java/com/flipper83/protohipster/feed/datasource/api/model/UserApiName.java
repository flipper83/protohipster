package com.flipper83.protohipster.feed.datasource.api.model;

/**
 * this is the complete user. it isthe title and the complete name. Example Mr John Doe
 */
public class UserApiName {

    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }
}
