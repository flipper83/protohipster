package com.flipper83.protohipster.feed.domain.boundaries;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FeedBoundary {
    private boolean success = false;
    List<HipsterBoundary> hipsters = new ArrayList<HipsterBoundary>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<HipsterBoundary> getHipsters() {
        return hipsters;
    }


    public void add(HipsterBoundary hipster) {
        hipsters.add(hipster);
    }
}
