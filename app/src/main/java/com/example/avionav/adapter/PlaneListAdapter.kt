package com.example.avionav.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avionav.databinding.HomeListItemBinding
import com.example.avionav.model.Plane
import com.squareup.picasso.Picasso

class PlaneListAdapter(val onClickListener: ONClickListener):
    ListAdapter<Plane,PlaneListAdapter.ListItemViewHolder>(DiffCallback) {


    class ListItemViewHolder(private var binding: HomeListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(plane: Plane){
                binding.planes = plane
                binding.executePendingBindings()
            }
        }

    companion object DiffCallback: DiffUtil.ItemCallback<Plane>() {
        override fun areItemsTheSame(oldItem: Plane, newItem: Plane): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Plane, newItem: Plane): Boolean {
            return oldItem == newItem
        }
    }

    class ONClickListener(val clickListener: (plane: Plane) -> Unit){
        fun onClick(plane: Plane) = clickListener(plane)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(HomeListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val data = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(data)
        }
        holder.bind(data)
    }
}