package com.example.feature_pet.pet.model


data class Dog(
    val name: String,
    var breed: String,
    var sex: DogSex,
    var age: String,
    var size: DogSize,
    val imageResId: Int
)

enum class DogSize {
    SMALL,
    MEDIUM,
    LARGE
}

enum class DogSex {
    MALE,
    FEMALE
}
