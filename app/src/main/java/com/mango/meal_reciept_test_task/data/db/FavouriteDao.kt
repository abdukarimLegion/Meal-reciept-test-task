package com.mango.meal_reciept_test_task.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealDB: MealDB)

    @Delete
    suspend fun delete(mealDB: MealDB)

    @Query("SELECT * FROM meal")
    fun getAllMeals(): Flow<List<MealDB>>
}