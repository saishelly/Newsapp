package com.techmania.springr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.techmania.springr.Model.Article
import com.techmania.springr.ViewModel.RecyclerAdapter

import com.techmania.springr.databinding.ActivityMainBinding
import com.techmania.springr.repository.Repository

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding
    lateinit var mainAdapter:RecyclerAdapter

    private lateinit var viewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

           mainBinding=ActivityMainBinding.inflate(layoutInflater)
        setupRecyclerView()




        val repository= Repository()


        val viewModelFactory =MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel :: class.java)


        var mycountry  : String ="us"
        var mycategory : String ="business"
        var mykey : String ="3d93a1da7b9146a2bc26b71eb834dc2f"
        viewModel.getPost(mycountry,mycategory,mykey)
        viewModel.myResponse.observe(this, Observer { response ->
           if(response.isSuccessful && response !=null){

               response.body()?.let { mainAdapter.setData(it) }



           }
            else {
               Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
           }
        })



    }


   private fun setupRecyclerView()= mainBinding.recycler.apply {

       mainAdapter =RecyclerAdapter()
       adapter=mainAdapter
       layoutManager= LinearLayoutManager(this@MainActivity)

   }


}