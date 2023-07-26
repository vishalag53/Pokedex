package com.vishalag53.pokedex.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vishalag53.pokedex.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    if(imgUrl == null)  imgView.setImageResource(R.drawable.pokeball)
    else {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
                .into(imgView)
        }
    }
}

@BindingAdapter("textIfNull")
fun textIfNull(view: View, text: String?){
    if (view is TextView){
        view.text = text ?: ""
    }
}