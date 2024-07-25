package com.mango.meal_reciept_test_task.data.network

import com.mango.meal_reciept_test_task.data.model.response.MealResponse
import com.mango.meal_reciept_test_task.util.ServerUrl
import retrofit2.http.GET


interface ApiService {

    @GET(ServerUrl.RANDOM)
    suspend fun getRandomMeal(): MealResponse


}