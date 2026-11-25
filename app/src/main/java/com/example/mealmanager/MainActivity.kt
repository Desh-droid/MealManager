
package com.example.mealmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mealmanager.data.repository.FoodRepository
import com.example.mealmanager.ui.theme.MealManagerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = FoodRepository(this)

        setContent {
            MealManagerTheme {
                FoodTestScreen(repository)
            }
        }
    }
}

@Composable
fun FoodTestScreen(repository: FoodRepository) {

    var keyword by remember { mutableStateOf("") }

    val loadResult = remember {
        runCatching { repository.getAllFoods() }
    }

    val foods = remember(keyword, loadResult) {
        if (loadResult.isSuccess) {
            repository.searchFoods(keyword)
        } else {
            emptyList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {

        Text(
            text = "MealManager DB 테스트",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (loadResult.isFailure) {
            Text(
                text = "DB 로딩 실패: " +
                        loadResult.exceptionOrNull()?.message
            )
            return@Column
        }

        Text("전체 음식: ${repository.getFoodCount()}개")

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = keyword,
            onValueChange = { keyword = it },
            label = { Text("음식 이름 검색") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text("검색 결과: ${foods.size}개")

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(foods) { food ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = food.name,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text("ID: ${food.id}")
                        Text("100g 기준 영양성분")
                        Text("칼로리: ${food.calories} kcal")
                        Text("탄수화물: ${food.carbohydrate} g")
                        Text("단백질: ${food.protein} g")
                        Text("지방: ${food.fat} g")
                    }
                }
            }
        }
    }
}
