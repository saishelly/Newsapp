package com.techmania.springr.myapi
import com.techmania.springr.util.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofit by lazy {


        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api1 : SimpleApi by lazy {
        retrofit .create(SimpleApi :: class.java)

    }
}