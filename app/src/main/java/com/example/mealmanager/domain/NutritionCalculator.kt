package com.example.mealmanager.domain

import com.example.mealmanager.data.model.Food
import com.example.mealmanager.data.model.MealFood
import com.example.mealmanager.data.model.Nutrition

object NutritionCalculator {

    // 섭취량에 따른 영양소 계산
    fun calculate(
        food: Food,
        amountGram: Double
    ): Nutrition {

        require(amountGram.isFinite() && amountGram >= 0) {
            "섭취량은 유효한 0 이상의 숫자여야 합니다."
        }

        val ratio = amountGram / 100.0

        return Nutrition(
            calories = food.calories * ratio,
            carbohydrate = food.carbohydrate * ratio,
            protein = food.protein * ratio,
            fat = food.fat * ratio
        )
    }

    // 여러 음식의 전체 영양소 계산
    fun calculateTotal(
        foods: List<MealFood>
    ): Nutrition {

        return foods.fold(Nutrition()) { total, item ->
            total + calculate(
                item.food,
                item.amountGram
            )
        }
    }
}