package com.flipper83.protohipster.feed.view.viewmodel.mapper;

import com.flipper83.protohipster.feed.domain.boundaries.HipsterBoundary;
import com.flipper83.protohipster.feed.view.viewmodel.HipsterViewModel;

/**
 *
 */
public interface HipsterViewMapper {
    public HipsterViewModel mapper(HipsterBoundary hipsterBoundary);
}
