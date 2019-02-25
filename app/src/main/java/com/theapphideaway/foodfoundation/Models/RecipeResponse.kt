package com.theapphideaway.foodfoundation.Models


import java.io.Serializable

data class RecipeResponse(
    var href: String?,
    var results: List<Result>?,
    var title: String?,
    var version: Double?
): Serializable