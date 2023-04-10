package com.appotronics.carplay_app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.appotronics.carplay_app.R;
import com.appotronics.carplay_app.repo.BannerDataBean;
import com.appotronics.carplay_app.viewholder.ImageTitleHolder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;


/**
 * 自定义布局，图片+标题
 */

public class AtmosphereAdapter extends BannerAdapter<BannerDataBean, ImageTitleHolder> {

    public AtmosphereAdapter(List<BannerDataBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_atmosphere, parent, false));
    }

    @Override
    public void onBindView(ImageTitleHolder holder, BannerDataBean data, int position, int size) {
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        options.transform(new CenterCrop(), roundedCorners);
        Glide.with(holder.imageView)
                .load(data.imageRes)
                .apply(options)
                .into(holder.imageView);
    }

}
