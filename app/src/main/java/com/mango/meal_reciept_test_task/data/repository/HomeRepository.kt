package com.mango.meal_reciept_test_task.data.repository

import com.mango.meal_reciept_test_task.data.model.response.MealResponse
import com.mango.meal_reciept_test_task.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HomeRepository {
    fun getRandomMeal(): Flow<MealResponse>
}

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : HomeRepository {
    override fun getRandomMeal(): Flow<MealResponse> = flow { emit(apiService.getRandomMeal()) }
}