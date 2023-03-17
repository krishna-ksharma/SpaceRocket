package com.extraaedge.assignment.spacerocket.ui.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.extraaedge.assignment.spacerocket.R
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.databinding.FragmentRocketListBinding
import com.extraaedge.assignment.spacerocket.ui.home.viewmodel.SpaceRocketViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketListFragment : Fragment() {
    private lateinit var binding: FragmentRocketListBinding
    private val viewModel: SpaceRocketViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.errorMessage.isVisible = false
            viewModel.listRockets()
        }
        setupAdapter()
        observeRockets()
    }

    private fun setupAdapter() {
        binding.rocketRecyclerView.apply {
            adapter =
                RocketListAdapter(requireContext()) { rocket ->
                    viewModel.setSelectedRocketItem(rocket)
                    findNavController().navigate(R.id.action_rocketListFragment_to_rocketDetailFragment)
                }
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun observeRockets() {
        viewModel.rockets.observe(viewLifecycleOwner) { result ->
            binding.swipeRefreshLayout.isRefreshing = false
            when (result) {
                is RocketResult.Success -> {
                    binding.rocketRecyclerView.isVisible = true
                    val adapter = binding.rocketRecyclerView.adapter as RocketListAdapter
                    adapter.setData(result.data)
                }
                is RocketResult.Error -> {
                    binding.rocketRecyclerView.isVisible = false
                    binding.errorMessage.isVisible = true
                    binding.errorMessage.text = result.message
                }
            }
        }
    }
}