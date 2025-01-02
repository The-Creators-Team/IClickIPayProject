package com.example.feature_pet.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.feature_pet.R
import com.example.feature_pet.pet.model.Guardian
import com.example.feature_pet.pet.model.GuardianCareSize
import java.security.Guard

class GuardianViewModel : ViewModel() {
    private val _guardianList = mutableStateListOf(
        Guardian(name = "Jim Joe", rating = 4, price = 30.50f, careSize = GuardianCareSize.MEDIUM, imageResId = R.drawable.dog_sitter_3),
        Guardian(name = "Harold Flower", rating = 3, price = 40.50f, careSize = GuardianCareSize.SMALL, imageResId = R.drawable.dog_sitter_4),
        Guardian(name = "Amy Example", rating = 2, price = 35.50f, careSize = GuardianCareSize.SMALL, imageResId = R.drawable.dog_sitter_1),
        Guardian(name = "Julia Heart", rating = 5, price = 20.00f, careSize = GuardianCareSize.LARGE, imageResId = R.drawable.dog_sitter_2)
    )

    val guardianList: List<Guardian> get()= _guardianList
}