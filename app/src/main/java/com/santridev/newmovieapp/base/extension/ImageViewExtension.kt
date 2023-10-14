package com.santridev.newmovieapp.base.extension

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

const val IMAGE_BASE_URL_POSTER = "https://image.tmdb.org/t/p/w185/"
const val IMAGE_BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/w1280"

fun ImageView.loadPoster(url: String) {
    Glide.with(this.context)
        .load("$IMAGE_BASE_URL_POSTER$url")
        .thumbnail(0.3f)
        .placeholder(ColorDrawable(Color.LTGRAY))
        .apply { RequestOptions.fitCenterTransform() }
        .into(this)
}

fun ImageView.loadBackdrop(url: String) {
    Glide.with(this.context)
        .load("$IMAGE_BASE_URL_BACKDROP$url")
        .thumbnail(0.3f)
        .placeholder(ColorDrawable(Color.LTGRAY))
        .apply { RequestOptions.fitCenterTransform() }
        .into(this)
}