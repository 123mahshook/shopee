package com.mahshook.shopee.ui.model.api

import com.mahshook.shopee.ui.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MealsWebService {

    private lateinit var api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://datausa.io/api/") /*"https://www.themealdb.com/api/json/v1/1/")*/
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    fun getMeals(): Call<MealsCategoriesResponse> {
        return api.getMeals()
    }

    interface MealsApi {
        @GET("data?drilldowns=Nation&measures=Population")
        fun getMeals(): Call<MealsCategoriesResponse>
    }

}