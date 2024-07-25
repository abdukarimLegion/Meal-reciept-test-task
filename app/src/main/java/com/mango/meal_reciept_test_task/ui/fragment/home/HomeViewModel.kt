package com.mango.meal_reciept_test_task.ui.fragment.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mango.meal_reciept_test_task.data.model.response.MealResponse
import com.mango.meal_reciept_test_task.domain.HomeInteractor
import com.mango.meal_reciept_test_task.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val interactor: HomeInteractor
) : ViewModel() {

    private val _screenState = MutableStateFlow<Resource<HomeScreenData>>(Resource.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getRandomMeal()
                .combine(
                    interactor.getRandomMeal()
                ){meal1, meal2 ->
                    meal1 to meal2
                }
                .catch { exception ->
                    _screenState.value = Resource.Error(exception, exception.message ?: "Unknown error")
                    Log.e("Error", "while getting data", exception)
                }
                .collectLatest { (m1, m2) ->
                    // mealResponse.meals ro'yxatini olish
                    _screenState.value = Resource.Success(HomeScreenData(m1, m2))

                }
        }
    }

}

data class HomeScreenData(
    val meal1: MealResponse,
    val meal2: MealResponse
)
