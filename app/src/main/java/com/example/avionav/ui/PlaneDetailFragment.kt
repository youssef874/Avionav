package com.example.avionav.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.avionav.MainActivity
import com.example.avionav.R
import com.example.avionav.databinding.FragmentPlaneDetailBinding
import com.example.avionav.libgdx.AndroidLauncher
import com.example.avionav.model.Plane

/**
* This class is a ui controller to the plain detail screen
*/
class PlaneDetailFragment : Fragment() {

    private lateinit var plane: Plane

    private lateinit var binding: FragmentPlaneDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Get the plane data sent by [HomeFragment]
        arguments?.let {
            plane = it.get("plane") as Plane
        }
        setHasOptionsMenu(true)

        (activity as MainActivity).supportActionBar?.title = plane.name
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlaneDetailBinding.inflate(inflater,container,false)

        //Change the title according to the plane name
        binding.test.text = plane.name


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.plain_detail_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.module_menu_item -> {
                launchLibgdx()
                true
            }else ->  super.onOptionsItemSelected(item)
        }
    }

    private fun launchLibgdx() {
        val intent = Intent(activity,AndroidLauncher::class.java)
        //send the plane data to libgdx
        intent.putExtra(PLANE,plane)
        startActivity(intent)
    }

    companion object{
        const val PLANE = "plane"
    }
}