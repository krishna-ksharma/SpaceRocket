package com.extraaedge.assignment.spacerocket.ui.home.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.extraaedge.assignment.spacerocket.databinding.RowRocketImageBinding

class RocketImagesAdapter(val context: Context, val onClick: (String) -> Unit) :
    RecyclerView.Adapter<RocketImagesAdapter.ViewHolder>() {
    private var images: List<String> = emptyList()

    fun setData(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RowRocketImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.imageUrl = imageUrl
            binding.root.setOnClickListener {
               onClick(imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRocketImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }
}