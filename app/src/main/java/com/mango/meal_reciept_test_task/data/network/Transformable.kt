package com.mango.meal_reciept_test_task.data.network

interface Transformable<T> {
    fun transform(): T
}