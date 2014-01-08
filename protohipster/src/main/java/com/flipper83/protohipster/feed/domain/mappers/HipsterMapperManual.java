package com.flipper83.protohipster.feed.domain.mappers;

import com.flipper83.protohipster.feed.domain.boundaries.HipsterBoundary;
import com.flipper83.protohipster.feed.domain.gateway.Hipster;

import javax.inject.Inject;

/**
 * this class mapping models without
 */
public class HipsterMapperManual implements HipsterMapper {
    @Inject
    public HipsterMapperManual() {
    }

    @Override
    public HipsterBoundary mapper(Hipster hipster) {
        HipsterBoundary hipsterBoundary = new HipsterBoundary();
        hipsterBoundary.setAvatar(hipster.getAvatar());
        hipsterBoundary.setUserId(hipster.getUserId());
        hipsterBoundary.setName(hipster.getName() + " " + hipster.getSurname());

        return hipsterBoundary;
    }

}
