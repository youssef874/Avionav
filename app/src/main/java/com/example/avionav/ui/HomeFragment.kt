package com.example.avionav.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.avionav.R
import com.example.avionav.adapter.PlaneListAdapter
import com.example.avionav.databinding.FragmentHomeBinding
import com.example.avionav.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recycleView.adapter = PlaneListAdapter(PlaneListAdapter.ONClickListener {
            viewModel.displayPlaneInformation(it)
        })

        viewModel.navigateToPlaneDetail.observe(viewLifecycleOwner,{
           if (it != null){
               val action = HomeFragmentDirections.actionHomeFragmentToPlainDetailFragment(it)
               findNavController().navigate(action)
               viewModel.displayPlaneInformationComplete()
           }

        })
        return binding.root
    }

    companion object{
        const val TAG = "HomeFragment"
    }
}