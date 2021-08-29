package com.example.avionav.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.avionav.databinding.HomeListItemBinding
import com.example.avionav.model.Plane

/**
 * This class will be the adapter of the home screen list
 * @param onClickListener: an lambda function get [Plane] as param to invoke it in this class
 */
class PlaneListAdapter(private val onClickListener: ONClickListener):
    ListAdapter<Plane,PlaneListAdapter.ListItemViewHolder>(DiffCallback) {

    /**
     * This class represent the list item view holder
     * @param binding: the list item layout through data binding
     */
    class ListItemViewHolder(private var binding: HomeListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(plane: Plane){
                binding.planes = plane
                binding.executePendingBindings()
            }
        }

    /**
     * This object help recycleView keep updated if the data changed
     */
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