
package com.example.mealmanager.data.repository

import android.content.Context
import com.example.mealmanager.data.local.FoodDataSource
import com.example.mealmanager.data.model.Food

class FoodRepository(context: Context) {

    private val dataSource =
        FoodDataSource(context.applicationContext)

    private val foods: List<Food> by lazy {
        dataSource.loadFoods()
    }

    // 전체 음식 목록 조회
    fun getAllFoods(): List<Food> {
        return foods
    }

    // 음식 이름으로 검색
    fun searchFoods(keyword: String): List<Food> {
        val query = keyword.trim()

        if (query.isEmpty()) {
            return foods
        }

        return foods.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    // ID로 특정 음식 조회
    fun getFoodById(id: Int): Food? {
        return foods.find { it.id == id }
    }

    // 전체 음식 개수 조회
    fun getFoodCount(): Int {
        return foods.size
    }
}
