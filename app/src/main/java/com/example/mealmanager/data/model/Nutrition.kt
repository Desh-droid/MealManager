
package com.example.mealmanager.data.model

data class Nutrition(
    val calories: Double = 0.0,
    val carbohydrate: Double = 0.0,
    val protein: Double = 0.0,
    val fat: Double = 0.0
) {
    operator fun plus(other: Nutrition): Nutrition {
        return Nutrition(
            calories = calories + other.calories,
            carbohydrate = carbohydrate + other.carbohydrate,
            protein = protein + other.protein,
            fat = fat + other.fat
        )
    }
}