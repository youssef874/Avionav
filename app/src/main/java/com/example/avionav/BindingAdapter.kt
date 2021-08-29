package com.example.avionav

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.avionav.adapter.PlaneListAdapter
import com.example.avionav.model.Plane

/**
 * Put the planes list to the recycle vie adapter using BindingAdapter of data biding
 * and call it in Home_fragment.xml in [RecyclerView] tag in app:listData
 * @param recyclerView: will get from xml
 * @param planes: the plane list will be in the xml as variable
 */
@BindingAdapter("listData")
fun binRecycleView(recyclerView: RecyclerView, planes:List<Plane>?){
    val adapter = recyclerView.adapter as PlaneListAdapter
    adapter.submitList(planes)
}

/**
 * Toad the img from the url using coil third party library
 * It will be used in home_lis_item.xml in [ImageView] tag in app:imageUrl
 * @param imageView: will get from xml
 * @param imageUrl: the url to be loaded
 */
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView,imageUrl: String?){
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imageUri){
            placeholder(R.drawable.loading_animation)
        }
    }
}