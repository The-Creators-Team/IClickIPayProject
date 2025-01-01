package com.example.feature_babysitter.babysitter

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.feature_babysitter.R

class BabySitterViewModel : ViewModel() {
    // Mutable state list for children
    private val _children = mutableStateListOf(
        Child("Chris", 22, R.drawable.cam_placeholder),
        Child("Rebecca", 22, R.drawable.cam_placeholder),
        Child("Jill", 22, R.drawable.cam_placeholder),
        Child("Leon", 22, R.drawable.cam_placeholder)
    )

    private val _babysitters = mutableStateListOf(
        Babysitter("Lee", "Corona", R.drawable.cam_placeholder, 3.0, 500, 15),
        Babysitter("Alice", "Corona", R.drawable.cam_placeholder, 3.0, 500, 15),
        Babysitter("Nina", "Corona", R.drawable.cam_placeholder, 3.0, 500, 15)
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