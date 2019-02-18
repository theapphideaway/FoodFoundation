package com.theapphideaway.foodfoundation.Models


import java.io.Serializable

data class Result(
    var href: String,
    var ingredients: String,
    var thumbnail: String,
    var title: String
): Serializable