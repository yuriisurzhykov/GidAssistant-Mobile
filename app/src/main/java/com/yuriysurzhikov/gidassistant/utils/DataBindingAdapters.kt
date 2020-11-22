package com.yuriysurzhikov.gidassistant.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yuriysurzhikov.gidassistant.R

@BindingAdapter("onboadring:src")
fun setImageUri(view: ImageView, imageUri: String?) {
    Glide.with(view)
        .load(imageUri)
        .centerCrop()
        .placeholder(R.drawable.ic_logo)
        .into(view)
/*    if (imageUri == null) {
        view.setImageURI(null)
    } else {
        view.setImageURI(Uri.parse(imageUri))
    }*/
}

@BindingAdapter("onboadring:src")
fun setImageUri(view: ImageView, imageUri: Uri?) {
    view.setImageURI(imageUri)
}

@BindingAdapter("onboadring:src")
fun setImageDrawable(view: ImageView, drawable: Drawable?) {
    view.setImageDrawable(drawable)
}

@BindingAdapter("onboadring:src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}