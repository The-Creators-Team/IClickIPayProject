package com.example.feature_pet.pet.model

data class Guardian(
    val name: String,
    val rating: Int,
    val price: Float,
    val careSize: GuardianCareSize,
    val imageResId: Int

)


enum class GuardianCareSize {
    SMALL,
    MEDIUM,
    LARGE
}
