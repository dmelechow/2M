package com.dmelechow.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageLoader {

    companion object {
        fun load(context: Context, url: String?, imageView: ImageView) {
            Glide.with(context).load(url)
                .into(imageView)
        }

        fun load(context: Context, url: Drawable?, imageView: ImageView) {
            Glide.with(context).load(url)
                .into(imageView)
        }
    }
}