package com.example.feature_pet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_pet.R
import com.example.feature_pet.pet.model.Dog
import com.example.feature_pet.pet.model.DogSex
import com.example.feature_pet.pet.model.DogSize

class DogViewModel : ViewModel() {
    private val _dogList = mutableStateListOf(
        Dog("Goldie", "Labrador", DogSex.FEMALE, "3", DogSize.MEDIUM, R.drawable.labrador),
        Dog("Julia", "Chihuahua", DogSex.FEMALE, "4", DogSize.SMALL, R.drawable.chihuahua),
        Dog("Dumptruck", "Bulldog", DogSex.MALE, "2", DogSize.SMALL, R.drawable.bulldog),
        Dog("Sparkles", "Pug", DogSex.MALE, "1", DogSize.SMALL, R.drawable.pug),
    )
    val dogList: List<Dog> get() = _dogList





    fun addDog(dog: Dog) {
_dogList.add(dog)
    }

    fun removeDog(dog: Dog) {
      _dogList.remove(dog)
    }

    fun updateDog(dog: Dog, index: Int){
        _dogList[index]=dog
    }

    fun getDog(index: Int): Dog{
        return dogList[index]
    }
}