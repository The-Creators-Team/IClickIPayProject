package com.example.feature_handyman.handyman.viewmodel

import android.os.Build
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class DateViewModel : ViewModel() {
    var selectedDate: LocalDate = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now()
    } else {
        TODO("VERSION.SDK_INT < O")
    }
        private set

    fun setSelectedDate(date: LocalDate) {
        selectedDate = date
    }
}