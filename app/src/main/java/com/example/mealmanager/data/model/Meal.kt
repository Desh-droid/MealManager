package com.example.mealmanager.data.model

import java.time.LocalDateTime

data class Meal(
    val id: Long = 0,
    val dateTime: LocalDateTime,
    val mealType: MealType,
    val photoUri: String? = null,
    val foods: List<MealFood>
)