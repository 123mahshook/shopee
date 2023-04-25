package com.mahshook.shopee.ui.meals

import androidx.lifecycle.ViewModel
import com.mahshook.shopee.ui.model.MealsRepository
import com.mahshook.shopee.ui.model.response.MealsCategoriesResponse

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}