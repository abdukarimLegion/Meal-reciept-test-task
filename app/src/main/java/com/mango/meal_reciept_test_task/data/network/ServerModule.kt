package com.mango.meal_reciept_test_task.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mango.meal_reciept_test_task.data.db.MealDB
import com.mango.meal_reciept_test_task.data.model.response.Meal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServerModule {

    @Provides
    fun getGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    @Named("Retrofit")
    fun getApiServiceRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://themealdb.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun getApiService(@Named("Retrofit") retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java)

    @Provides
    @Singleton
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY //TODO: change before release
                }
            )
            .build()
    }

//    @Provides
//    fun provideDao(database: Meal): MealDB = database.getDao()
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext app: Context): ArticleDatabase {
//        return Room.databaseBuilder(
//            app,
//            ArticleDatabase::class.java,
//            "article_database"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }
}