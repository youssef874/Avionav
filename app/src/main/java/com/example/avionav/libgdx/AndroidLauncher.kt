package com.example.avionav.libgdx

import com.badlogic.gdx.backends.android.AndroidApplication
import android.os.Bundle
import android.util.Log
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.example.avionav.model.Plane
import com.example.avionav.ui.PlaneDetailFragment

/**
 * This class responsible on initialize the game
 */
class AndroidLauncher : AndroidApplication() {

    private lateinit var plane: Plane

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        //get the plane data
        plane = intent?.extras?.getParcelable<Plane>(PlaneDetailFragment.PLANE) as Plane
        initialize(MyGdxGame(plane), config)
    }

}