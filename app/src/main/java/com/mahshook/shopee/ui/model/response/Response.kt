package com.mahshook.shopee.ui.model.response

import com.google.gson.annotations.SerializedName

data class MealsCategoriesResponse(@SerializedName("data")val categories: List<MealResponse>)

data class MealResponse(
    @SerializedName("ID Nation") val id: String,
    @SerializedName("Nation") val nation: String,
    @SerializedName("ID Year") val idYear: String,
    @SerializedName("Year") val Year: String,
    @SerializedName("Population") val Population: String,
    @SerializedName("Slug Nation") val slugNation: String
)