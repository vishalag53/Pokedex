package com.vishalag53.pokedex.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vishalag53.pokedex.R.drawable

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    if(imgUrl == null)  imgView.setImageResource(drawable.pokeball)
    else {
        imgUrl.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                    .placeholder(drawable.loading_animation)
                    .error(drawable.ic_broken_image))
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