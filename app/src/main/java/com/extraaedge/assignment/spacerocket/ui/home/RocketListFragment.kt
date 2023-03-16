package com.extraaedge.assignment.spacerocket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.extraaedge.assignment.spacerocket.R
import com.extraaedge.assignment.spacerocket.databinding.FragmentRocketListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketListFragment : Fragment() {
    lateinit var binding: FragmentRocketListBinding
    private val viewModel: SpaceRocketViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.listRockets()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        viewModel.state.observe(viewLifecycleOwner) { rockets ->
            (binding.rocketRecyclerView.adapter as RocketListAdapter).setData(rockets)
        }
    }
}