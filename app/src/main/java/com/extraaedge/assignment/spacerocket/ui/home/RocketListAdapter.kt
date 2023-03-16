package com.extraaedge.assignment.spacerocket.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import com.extraaedge.assignment.spacerocket.databinding.RowItemRocketBinding

class RocketListAdapter(val context: Context, val onClick: (Rocket) -> Unit) :
    RecyclerView.Adapter<RocketListAdapter.ViewHolder>() {
    private var rockets: List<Rocket> = emptyList()

    fun setData(newRockets: List<Rocket>) {
        rockets = newRockets
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RowItemRocketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rocket: Rocket) {
            binding.rocket = rocket
            binding.root.setOnClickListener {
                onClick(rocket)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowItemRocketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return rockets.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rockets[position])
    }
}