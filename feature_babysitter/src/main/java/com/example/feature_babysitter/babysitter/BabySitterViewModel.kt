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
        Babysitter("Steve","Steve Harrington is a fictional character from the Netflix television show Stranger Things, portrayed by Joe Keery. Though initially characterized as a stereotypically unlikeable jock, Steve has progressed throughout the series into a more courageous, thoughtful, and caring character.", "StrangeThings", R.drawable.steve, 1.5, 33.87901699889791, -84.45645900148536,8.0, 15),
        Babysitter("Mary Poppins","Mary Poppins is described as \"practically perfect in every way\". She's kind and nurturing, but also firm and authoritative. She's sweet and has a big heart, but she's also demanding and doesn't settle.", "Corona", R.drawable.merry_poppins, 5.0, 33.916200120275384, -84.46743627362048,1.0, 40),
        Babysitter("Mrs.Doubtfire","Mrs.Doubtfire is a 1993 comedy film about an unemployed actor who disguises himself as an elderly British nanny to spend more time with his children", "Corona", R.drawable.doubtfire, 3.0, 33.92095879668887,-84.46697881555114,2.5, 25)
    )

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