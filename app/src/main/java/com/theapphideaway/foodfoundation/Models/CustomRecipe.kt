package com.theapphideaway.foodfoundation.Models

import java.io.Serializable

data class CustomRecipe(
    var id: Int,
    var title: String,
    var instructions: String,
    var ingredients: String,
    var pictureUrl: String
    ): Serializable