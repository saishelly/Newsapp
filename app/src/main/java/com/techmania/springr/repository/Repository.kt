package com.techmania.springr.repository

import com.techmania.springr.Model.Article
import com.techmania.springr.myapi.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getPost(country:String,category:String,key:String) : Response<List<Article>>
    {

        return   RetrofitInstance.api1.getPost(country,category,key)
    }
}