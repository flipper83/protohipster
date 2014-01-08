package com.flipper83.protohipster.feed.view.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.FontAwesomeText;
import com.flipper83.protohipster.R;
import com.flipper83.protohipster.feed.view.viewmodel.HipsterViewModel;
import com.flipper83.protohipster.uibase.transformation.TransformationBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 *
 */
public class FeedAdapter extends BaseAdapter {
    private static final int NUM_STARS = 5;
    private static final String ICON_HEART_FILL = "fa-heart";
    private static final String ICON_HEART_EMPTY = "fa-heart-o";
    private final Context context;
    private final Picasso picasso;
    private final TransformationBuilder transformationBuilder;
    private List<HipsterViewModel> feed;

    public FeedAdapter(Context context, List<HipsterViewModel> feed, Picasso picasso,
                       TransformationBuilder transformationBuilder) {
        this.context = context;
        this.feed = feed;
        this.picasso = picasso;
        this.transformationBuilder = transformationBuilder;
    }

    @Override
    public int getCount() {
        return feed.size();
    }

    @Override
    public Object getItem(int i) {
        return feed.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_feed, viewGroup, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.hipster_name);
        ImageView ivAvatar = (ImageView) convertView.findViewById(R.id.hipster_avatar);
        FontAwesomeText iconLikedByMe = (FontAwesomeText) convertView.findViewById(R.id.b_heart);
        FontAwesomeText icons[] = obtainIcons(convertView);

        HipsterViewModel hipsterViewModel = feed.get(position);

        name.setText(hipsterViewModel.getName());

        String iconLiked = (hipsterViewModel.isLikedByMe())?ICON_HEART_FILL:ICON_HEART_EMPTY;
        iconLikedByMe.setIcon(iconLiked);

        //set stars
        for (int i = 0; i < NUM_STARS; i++) {

            if (i < hipsterViewModel.getRating()) {
                icons[i].setIcon(ICON_HEART_FILL);
            } else {
                icons[i].setIcon(ICON_HEART_EMPTY);
            }
        }

        if (!hipsterViewModel.getUrlAvatar().isEmpty()) {
            picasso.load(hipsterViewModel.getUrlAvatar())
                    .centerCrop()
                    .fit()
                    .transform(transformationBuilder.createAvatarTransformation())
                    .into(ivAvatar);
        }


        return convertView;
    }

    private FontAwesomeText[] obtainIcons(View convertView) {
        FontAwesomeText[] returnIcons = new FontAwesomeText[NUM_STARS];

        returnIcons[0] = (FontAwesomeText) convertView.findViewById(R.id.star1);
        returnIcons[1] = (FontAwesomeText) convertView.findViewById(R.id.star2);
        returnIcons[2] = (FontAwesomeText) convertView.findViewById(R.id.star3);
        returnIcons[3] = (FontAwesomeText) convertView.findViewById(R.id.star4);
        returnIcons[4] = (FontAwesomeText) convertView.findViewById(R.id.star5);

        return returnIcons;
    }
}
