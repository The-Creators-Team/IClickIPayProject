package com.example.feature_learn.model

data class Tutor(
    val name: String,
    val rating: Int,
    val price: Float,
    val level: TutorLevel,
    val subject: TutorSubject,
    val imageResId: Int
)

enum class TutorLevel{
    ELEMENTARY,
    MIDDLE,
    HIGH,
    COLLEGE
}

enum class TutorSubject{
    ENGLISH,
    MATH,
    SCIENCE,
    ART,
    MUSIC
}
