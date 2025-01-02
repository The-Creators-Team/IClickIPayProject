package com.example.feature_housecleaning.housecleaning

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.feature_housecleaning.R

class HouseCleaningViewModel : ViewModel(){

    private val _cleaners = mutableStateListOf(
        Cleaner("Lee", "Corona", R.drawable.cam_placeholder,3.0,500,15 ),
        Cleaner("Alice", "Corona", R.drawable.cam_placeholder,3.0,500,15 ),
        Cleaner("Nina", "Corona", R.drawable.cam_placeholder,3.0,500,15 )
    )

    private val _houses = mutableStateListOf(
        House("white House", 100, R.drawable.cam_placeholder),
        House("Red House", 100, R.drawable.cam_placeholder)
    )

    val cleaners: List<Cleaner> get() = _cleaners
    val houses: List<House> get() = _houses

    // Add a new child
    fun addCleaner(cleaner: Cleaner) {
        _cleaners.add(cleaner)
    }
    fun addHouse(house:House) {
        _houses.add(house)
    }

    // Remove a child
    fun removeCleaner(cleaner: Cleaner) {
        _cleaners.remove(cleaner)
    }
    fun removeHouse(house:House) {
        _houses.remove(house)
    }

    // Update a child's information
    fun updateCleaner(index: Int, updatedCleaner: Cleaner) {
        if (index in _cleaners.indices) {
            _cleaners[index] = updatedCleaner
        }
    }
    fun updateHouse(index: Int, updatedHouse:House) {
        if (index in _houses.indices) {
            _houses[index] = updatedHouse
        }
    }

}