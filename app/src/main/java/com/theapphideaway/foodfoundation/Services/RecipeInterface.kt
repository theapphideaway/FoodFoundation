package com.theapphideaway.foodfoundation.Services

import com.theapphideaway.foodfoundation.Models.RecipeResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeInterface {

    @GET("api/")
    fun getSearchedRecipes(
        @Query("q") dish: String
    ): Deferred<RecipeResponse>

}