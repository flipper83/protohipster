package com.flipper83.protohipster.globalutils.rating;

/**
 * This provide a way to calculate Ratings
 */
public interface RatingCalculator{
    int calculate(int totalLikers);

    int defaultValue();
}
