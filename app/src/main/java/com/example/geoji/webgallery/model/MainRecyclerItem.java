package com.example.geoji.webgallery.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Geoji on 2016-06-24.
 */
public class MainRecyclerItem {
    private String imageUrl;
    private String imageText;

    public MainRecyclerItem(String imageUrl, String imageText){
        this.imageUrl = imageUrl;
        this.imageText = imageText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url){
        Glide.with(view.getContext()).load(url).into(view);
    }
}
