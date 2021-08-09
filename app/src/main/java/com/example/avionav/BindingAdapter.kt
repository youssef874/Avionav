package com.example.avionav

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.avionav.adapter.PlaneListAdapter
import com.example.avionav.model.Plane

@BindingAdapter("listData")
fun binRecycleView(recyclerView: RecyclerView, planes:List<Plane>?){
    val adapter = recyclerView.adapter as PlaneListAdapter
    adapter.submitList(planes)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView,imageUrl: String?){
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imageUri){
            placeholder(R.drawable.loading_animation)
        }
    }
}