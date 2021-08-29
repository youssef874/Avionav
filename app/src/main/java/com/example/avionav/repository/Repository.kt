package com.example.avionav.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

/**
 * Yis class responsible in retrieve the planeInfo from firebase real time database
 * within any reference and return them as live s80t by inherit from [LiveData]
 * and implement [ValueEventListener]
 * @constructor : take the reference to fetch
 */
class Repository(private val reference: DatabaseReference):LiveData<DataSnapshot?>(),ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        value = snapshot
    }

    override fun onCancelled(error: DatabaseError) {
        Log.e("DataSnapshotLiveData","error s80t retrieving")
    }

    /**
     * As soon as this class active add the listener to the reference
     */
    override fun onActive() {
        reference.addValueEventListener(this)
    }

    /**
     * Once this class is inactive retrieve the listener
     */
    override fun onInactive() {
        reference.removeEventListener(this)
    }

}