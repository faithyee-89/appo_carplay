package com.appotronics.carplay_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appotronics.carplay_app.R;
import com.appotronics.carplay_app.dialog.ShareDialog;
import com.appotronics.carplay_app.repo.BannerDataBean;
import com.appotronics.carplay_app.viewholder.ImageTitleHolder;
import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;


/**
 * 自定义布局，图片+标题
 */

public class ImageTitleAdapter extends BannerAdapter<BannerDataBean, ImageTitleHolder> {
    public OnItemShareClickListener onItemShareClickListener;

    public ImageTitleAdapter(List<BannerDataBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_image_title, parent, false));
    }

    @Override
    public void onBindView(ImageTitleHolder holder, BannerDataBean data, int position, int size) {
        Glide.with(holder.imageView)
                .load(data.imageRes)
                .into(holder.imageView);
        holder.title.setText(data.title);
        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemShareClickListener != null) {
                    onItemShareClickListener.onShare(data);
                }
            }
        });
    }

    public void setOnItemShareClickListener(OnItemShareClickListener onItemShareClickListener) {
        this.onItemShareClickListener = onItemShareClickListener;
    }

    public interface OnItemShareClickListener {
        void onShare(BannerDataBean data);
    }

}
