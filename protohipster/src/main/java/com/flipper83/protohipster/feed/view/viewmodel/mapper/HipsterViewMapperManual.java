package com.flipper83.protohipster.feed.view.viewmodel.mapper;

import com.flipper83.protohipster.feed.domain.boundaries.HipsterBoundary;
import com.flipper83.protohipster.feed.view.viewmodel.HipsterViewModel;

import javax.inject.Inject;

/**
 *
 */
public class HipsterViewMapperManual implements HipsterViewMapper {

    @Inject
    public HipsterViewMapperManual(){

    }

    @Override
    public HipsterViewModel mapper(HipsterBoundary hipsterBoundary) {
        HipsterViewModel viewModel = new HipsterViewModel();

        viewModel.setName(hipsterBoundary.getName());
        viewModel.setUrlAvatar(hipsterBoundary.getAvatar());
        viewModel.setRating(hipsterBoundary.getRating());
        viewModel.setUserId(hipsterBoundary.getUserId());

        return viewModel;
    }
}
