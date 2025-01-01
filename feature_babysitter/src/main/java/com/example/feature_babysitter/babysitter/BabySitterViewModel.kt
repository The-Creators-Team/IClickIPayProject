package com.example.feature_babysitter.babysitter

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.feature_babysitter.R

class BabySitterViewModel : ViewModel() {
    // Mutable state list for children
    private val _children = mutableStateListOf(
        Child("Tommy Pickles","Male", 3, R.drawable.tommy),
        Child("Stewie","Male", 2, R.drawable.stewie),
    )

    private val _babysitters = mutableStateListOf(
        Babysitter("Steve","Steve Harrington is a fictional character from the Netflix television show Stranger Things, portrayed by Joe Keery.", "StrangeThings", R.drawable.steve, 1.5, 33.87901699889791, -84.45645900148536,8.0, 15.0),
        Babysitter("Mary Poppins","Mary Poppins is described as \"practically perfect in every way\". She's kind and nurturing, but also firm and authoritative.", "Corona", R.drawable.merry_poppins, 5.0, 33.916200120275384, -84.46743627362048,1.0, 40.0),
        Babysitter("Mrs.Doubtfire","Mrs.Doubtfire is a 1993 comedy film about an unemployed actor who disguises himself as an elderly British nanny to spend more time with his children", "Riverside", R.drawable.doubtfire, 3.0, 33.92095879668887,-84.46697881555114,2.5, 25.0),
        Babysitter("The Nanny","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "New York", R.drawable.doubtfire, 3.0, 33.92095879668887,-84.46697881555114,2.5, 25.0),
        Babysitter("Nanny Mcphee","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "New Jersey", R.drawable.the_nanny, 1.0, 33.92095879668887,-84.46697881555114,10.5, 5.0),
        Babysitter("Mrs.Doubtfire","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "Corona", R.drawable.doubtfire, 4.0, 33.92095879668887,-84.46697881555114,20.0, 25.0),
        Babysitter("Mrs.Doubtfire","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "Corona", R.drawable.doubtfire, 2.0, 33.92095879668887,-84.46697881555114,15.0, 30.0),
        Babysitter("Mrs.Doubtfire","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "Corona", R.drawable.doubtfire, 3.0, 33.92095879668887,-84.46697881555114,25.0, 10.0),
        Babysitter("Mrs.Doubtfire","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "Corona", R.drawable.doubtfire, 4.5, 33.92095879668887,-84.46697881555114,2.0, 11.0),
        Babysitter("Mrs.Doubtfire","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "Corona", R.drawable.doubtfire, 2.5, 33.92095879668887,-84.46697881555114,3.0, 5.0),
        Babysitter("Mrs.Doubtfire","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "Corona", R.drawable.doubtfire, 5.0, 33.92095879668887,-84.46697881555114,50.0, 60.0),
        Babysitter("Mrs.Doubtfire","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "Corona", R.drawable.doubtfire, 2.0, 33.92095879668887,-84.46697881555114,70.0, 60.0),
        Babysitter("Mrs.Doubtfire","When applying for a nanny position, you can highlight relevant childcare experience, education, or certifications in child development.", "Corona", R.drawable.doubtfire, 3.0, 33.92095879668887,-84.46697881555114,2.5, 10.0)
    )

    private fun sortBabysittersByRating(): List<Babysitter> {
        return _babysitters.sortedByDescending { it.rating }
    }
     val recommendSortBabysitter: List<Babysitter> get() = sortBabysittersByRating()

    private fun sortBabysittersByCostPerHour(): List<Babysitter> {
        return _babysitters.sortedByDescending { it.costPerHour }
    }
    val priceSortBabysitter: List<Babysitter> get() = sortBabysittersByCostPerHour()

    private fun sortBabysittersByDistance(): List<Babysitter> {
        return _babysitters.sortedByDescending { it.distance }
    }
    val distanceSortBabysitter: List<Babysitter> get() = sortBabysittersByDistance()



    // Publicly exposed list
    val children: List<Child> get() = _children
    val babysitters: List<Babysitter> get() = _babysitters

    // Add a new child
    fun addChild(child: Child) {
        _children.add(child)
    }
    fun addBabysitter(babysitter: Babysitter) {
        _babysitters.add(babysitter)
    }

    // Remove a child
    fun removeChild(child: Child) {
        _children.remove(child)
    }
    fun removeBabysitter(babysitter: Babysitter) {
        _babysitters.remove(babysitter)
    }

    // Update a child's information
    fun updateChild(index: Int, updatedChild: Child) {
        if (index in _children.indices) {
            _children[index] = updatedChild
        }
    }
    fun updateBabysitter(index: Int, updatedBabysitter: Babysitter) {
        if (index in _babysitters.indices) {
            _babysitters[index] = updatedBabysitter
        }
    }


}