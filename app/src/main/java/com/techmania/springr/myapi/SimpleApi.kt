package com.techmania.springr.myapi
import com.techmania.springr.Model.Article
import com.techmania.springr.Model.PostX
import retrofit2.Response
import  retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface SimpleApi {
    @GET("v2/top-headlines")
    suspend fun getPost(
        @Query("country")country:String,
        @Query("category")category:String,
        @Query("key")key :String,
    ) : Response <List<Article>>
}