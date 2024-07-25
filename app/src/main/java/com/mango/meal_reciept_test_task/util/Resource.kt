package com.mango.meal_reciept_test_task.util

sealed class Resource<out T>(val detail : String?) {
    object Empty : Resource<Nothing>(null)
    object Loading: Resource<Nothing>(null)
    class Success<out T : Any>(val data: T) : Resource<T>(null)
    class Error<out T : Any>(val e: Throwable, message: String) : Resource<T>(message)
}
