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
import com.example.avionav.model.Plane
import com.example.avionav.viewModel.HomeViewModel

/**
 * This class is a ui controller to the home screen
 */
class HomeFragment : Fragment() {

    //The layout instance using data binding
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        //declare this fragment as the lifecycle owner fo the layout
        binding.lifecycleOwner = this
        //Set the viewModel variable in the fragment_home.xml to the viewModel var in this class
        binding.viewModel = viewModel

        //Set the RecycleView adapter
        binding.recycleView.adapter = PlaneListAdapter(PlaneListAdapter.ONClickListener {
            viewModel.displayPlaneInformation(it)
        })

        viewModel.navigateToPlaneDetail.observe(viewLifecycleOwner,{
           if (it != null){
               navigateToPlaneDetailFragment(it)
               viewModel.displayPlaneInformationComplete()
           }

        })
        return binding.root
    }

    /**
     * Navigate to PlaneDetailFragment using navigation component
     * @param plane: the plane date we sent to destination
     */
    private fun navigateToPlaneDetailFragment(plane: Plane){
        val action = HomeFragmentDirections.actionHomeFragmentToPlainDetailFragment(plane)
        findNavController().navigate(action)
    }

}