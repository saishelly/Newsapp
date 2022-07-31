package com.techmania.springr

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techmania.springr.Model.Article


import com.techmania.springr.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {


    val myResponse : MutableLiveData<Response<List<Article>>> = MutableLiveData()
    fun getPost(country:String,category:String,key:String)
    {

        viewModelScope.launch {
            val response : Response<List<Article>> = repository.getPost(country, category, key)
            myResponse.value= response



        }

    }

}