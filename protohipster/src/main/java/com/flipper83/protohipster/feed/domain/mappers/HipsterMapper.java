package com.flipper83.protohipster.feed.domain.mappers;

import com.flipper83.protohipster.feed.domain.boundaries.HipsterBoundary;
import com.flipper83.protohipster.feed.domain.gateway.Hipster;

/**
 *
 */
public interface HipsterMapper {
    public HipsterBoundary mapper(Hipster hipster);
}
