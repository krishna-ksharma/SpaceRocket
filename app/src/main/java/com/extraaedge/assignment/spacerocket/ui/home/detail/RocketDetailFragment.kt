package com.extraaedge.assignment.spacerocket.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.extraaedge.assignment.spacerocket.databinding.FragmentRocketDetailBinding
import com.extraaedge.assignment.spacerocket.viewmodel.SpaceRocketViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RocketDetailFragment : Fragment() {
    lateinit var binding: FragmentRocketDetailBinding
    private val viewModel: SpaceRocketViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        viewModel.selectedRocket.observe(viewLifecycleOwner) { rocket ->
            (activity as AppCompatActivity).supportActionBar?.title = rocket.name
            binding.rocket = rocket
            (binding.rocketImagesRecyclerView.adapter as RocketImagesAdapter).setData(rocket.flickr_images)
        }
    }

    private fun setupAdapter() {
        binding.rocketImagesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = RocketImagesAdapter(requireContext()) { imageUrl ->

            }
        }
    }
}