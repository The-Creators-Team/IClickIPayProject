package com.example.feature_handyman.handyman.viewmodel

import androidx.lifecycle.ViewModel
import com.example.feature_handyman.handyman.data.SelectionData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HandymanViewModel : ViewModel() {
    private val _selectedNeed = MutableStateFlow("")
    val selectedNeed: StateFlow<String> = _selectedNeed

    private val _selectedProblem = MutableStateFlow("")
    val selectedProblem: StateFlow<String> = _selectedProblem

    fun updateSelectedNeed(need: String) {
        _selectedNeed.value = need
    }

    fun updateSelectedProblem(problem: String) {
        _selectedProblem.value = problem
    }
}