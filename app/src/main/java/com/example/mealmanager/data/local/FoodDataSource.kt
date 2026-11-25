
package com.example.mealmanager.data.local

import android.content.Context
import com.example.mealmanager.data.model.Food

class FoodDataSource(private val context: Context) {

    fun loadFoods(): List<Food> {
        val foods = mutableListOf<Food>()

        context.assets.open("foods.tsv")
            .bufferedReader(Charsets.UTF_8)
            .useLines { lines ->

                lines.drop(1).forEach { line ->

                    val columns = line.split("\t")

                    if (columns.size == 6) {
                        val food = Food(
                            id = columns[0].toInt(),
                            name = columns[1],
                            calories = columns[2].toDouble(),
                            carbohydrate = columns[3].toDouble(),
                            protein = columns[4].toDouble(),
                            fat = columns[5].toDouble()
                        )

                        foods.add(food)
                    }
                }
            }

        return foods
    }
}
