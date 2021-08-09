package com.example.avionav.viewModel

import android.media.Image
import androidx.lifecycle.*
import com.example.avionav.model.Plane
import com.example.avionav.repository.Repository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel: ViewModel() {
    private val database = Firebase.database.reference

    private var _planes = MutableLiveData<List<Plane>>()
    val plane: LiveData<List<Plane>>
        get() = _planes

    private var _navigateToPlaneDetail = MutableLiveData<Plane?>()
    val navigateToPlaneDetail: LiveData<Plane?>
    get() = _navigateToPlaneDetail

    private val planeSnapshot = Repository(database.child("planesIntro"))

    init {
        _planes =(planeSnapshot as LiveData<DataSnapshot>).map {
            val list = ArrayList<Plane>()
            if (it != null){
                for (data in it.children)
                    data.getValue<Plane>()?.let { it1 -> list.add(it1) }
            }
            return@map list
        } as MutableLiveData<List<Plane>>
    }

    fun displayPlaneInformation(plane: Plane){
        _navigateToPlaneDetail.value = plane
    }

    fun displayPlaneInformationComplete(){
        _navigateToPlaneDetail.value = null
    }
}