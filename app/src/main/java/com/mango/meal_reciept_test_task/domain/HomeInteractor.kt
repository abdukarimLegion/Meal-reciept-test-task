package com.mango.meal_reciept_test_task.domain

import com.mango.meal_reciept_test_task.data.repository.HomeRepository
import com.mango.meal_reciept_test_task.data.repository.HomeRepositoryImpl
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val homeRepository: HomeRepositoryImpl) {
    fun getRandomMeal() = homeRepository.getRandomMeal()
}