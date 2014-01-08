package com.flipper83.protohipster.globalutils.rating;

import javax.inject.Inject;

/**
 *
 */
public class RatingCalculatorFiveStars implements RatingCalculator {

    public final static int FIVE_STARTS_NUM_LIKES = 20;
    public final static int NUM_STARS = 5;
    private static final int DEFAULT_VALUE = 0;

    @Inject
    public RatingCalculatorFiveStars(){

    }

    @Override
    public int calculate(int totalLikers) {

        float rating = (totalLikers * NUM_STARS) / FIVE_STARTS_NUM_LIKES;

        if (rating > NUM_STARS) {
            rating = NUM_STARS;
        }

        return Math.round(rating);
    }

    @Override
    public int defaultValue() {
        return DEFAULT_VALUE;
    }
}
