package com.example.avionav.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.avionav.R
import com.example.avionav.databinding.FragmentPlaneDetailBinding
import com.example.avionav.model.Plane

class PlaneDetailFragment : Fragment() {

    private lateinit var plane: Plane

    private lateinit var binding: FragmentPlaneDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            plane = it.get("plane") as Plane
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG,"$plane")
        binding = FragmentPlaneDetailBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        binding.test.text = plane.name

        return binding.root
    }

    companion object{
        const val TAG = "HomeFragment"
    }
}