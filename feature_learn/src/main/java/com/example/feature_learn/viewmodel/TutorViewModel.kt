package com.example.feature_learn.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.feature_learn.R
import com.example.feature_learn.model.Tutor
import com.example.feature_learn.model.TutorLevel
import com.example.feature_learn.model.TutorSubject

class TutorViewModel: ViewModel() {
    private val _tutorList= mutableStateListOf(
        Tutor(name = "Johnny Teacher", rating = 3, price = 20.05f, level = TutorLevel.MIDDLE, subject = TutorSubject.MATH, imageResId = R.drawable.teacher_1),
        Tutor(name = "Sally Stolt", rating = 4, price = 40.00f, level = TutorLevel.MIDDLE, subject = TutorSubject.MATH, imageResId = R.drawable.teacher_2),
        Tutor(name = "Meginnis Waskom", rating = 3, price = 20.05f, level = TutorLevel.MIDDLE, subject = TutorSubject.MATH, imageResId = R.drawable.teacher_3),
        Tutor(name = "Jimmy Tutor", rating = 5, price = 30.00f, level = TutorLevel.MIDDLE, subject = TutorSubject.MATH, imageResId = R.drawable.teacher_4),
        )
    val tutorList: List<Tutor> get()= _tutorList
}