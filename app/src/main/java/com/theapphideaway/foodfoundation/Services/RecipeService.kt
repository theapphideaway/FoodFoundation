package com.theapphideaway.foodfoundation.Services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeService {

    private val BASE_URL = "http://www.recipepuppy.com/"
    private var mRetrofit: Retrofit? = null

    init {

        val okHttpClient = OkHttpClient.Builder()
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        println(mRetrofit)
    }

    fun getRecipeApi(): RecipeInterface{
        return mRetrofit!!.create<RecipeInterface>(RecipeInterface::class.java!!)
    }
}