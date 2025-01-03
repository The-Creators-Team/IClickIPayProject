package com.example.feature_housecleaning.housecleaning.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.feature_babysitter.babysitter.data.Babysitter
import com.example.feature_housecleaning.R

class HouseCleaningViewModel : ViewModel(){

    private val _cleaners = mutableStateListOf(
        Cleaner("Consuela", "I clean house", "Corona",R.drawable.consuela,3.0,50.0, 5.0, 2.0, 5.3, 20.0),
        Cleaner("Mr.Clean", "I clean house", "Corona",R.drawable.mr_clean,1.1,10.0, 6.0, 2.0, 5.3, 15.0),
        Cleaner("Joe Dirt", "I clean house", "Corona",R.drawable.joe_dirt,5.0,25.0, 2.0, 2.0, 5.3, 7.0),
        Cleaner("Mary Poppins", "I clean house", "Corona",R.drawable.merry_poppins,4.0,9.0, 3.0, 2.0, 5.3, 5.0),
        Cleaner("Mrs.Doubtfire", "I clean house", "Corona",R.drawable.doubtfire,2.5,200.0, 10.0, 3.0, 5.3, 4.0)
    )

    private val _houses = mutableStateListOf(
        House("white House", 100, 3, 2, "none", 14, date = "tbd")
    )

    private fun sortCleanersByRating(): List<Cleaner> {
        return _cleaners.sortedByDescending { it.rating }
    }
    val recommendSortCleaner: List<Cleaner> get() = sortCleanersByRating()

    private fun sortCleanersByCostPerHour(): List<Cleaner> {
        return _cleaners.sortedByDescending { it.costPerHour }
    }
    val priceSortBabysitter: List<Cleaner> get() = sortCleanersByCostPerHour()

    private fun sortCleanersByDistance(): List<Cleaner> {
        return _cleaners.sortedByDescending { it.distance }
    }
    val distanceSortBabysitter: List<Cleaner> get() = sortCleanersByDistance()


    val cleaners: List<Cleaner> get() = _cleaners
    val houses: List<House> get() = _houses

    // Add a new child
    fun addCleaner(cleaner: Cleaner) {
        _cleaners.add(cleaner)
    }
    fun addHouse(house: House) {
        _houses.add(house)
    }

    // Remove a child
    fun removeCleaner(cleaner: Cleaner) {
        _cleaners.remove(cleaner)
    }
    fun removeHouse(house: House) {
        _houses.remove(house)
    }

    // Update a child's information
    fun updateCleaner(index: Int, updatedCleaner: Cleaner) {
        if (index in _cleaners.indices) {
            _cleaners[index] = updatedCleaner
        }
    }
    fun updateHouse(index: Int, updatedHouse: House) {
        if (index in _houses.indices) {
            _houses[index] = updatedHouse
        }
    }

}