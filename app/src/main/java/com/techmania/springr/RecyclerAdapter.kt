package com.techmania.springr



import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.techmania.springr.MainActivity
import com.techmania.springr.Model.Article
import com.techmania.springr.View.Activity2
import com.techmania.springr.databinding.CardviewBinding




lateinit var binding3 : CardviewBinding
var url : String ="https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=3d93a1da7b9146a2bc26b71eb834dc2f"

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>()
{


    lateinit var context :Context

    private var myList = emptyList<Article>()

    fun RecyclerAdapter( list: List<Article> , context: Context) {
        this.myList = list
        this.context = context
    }
    inner   class MyViewHolder(val binding3:CardviewBinding) : RecyclerView.ViewHolder(binding3.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))           }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding3.apply {

            val list1 = myList[position]
            Title.text = list1.title
            ImageUrl.text = list1.urlToImage
            Picasso.get().load(myList.get(position).urlToImage).into(firstImage)

            firstImage.setOnClickListener{
                var   intent  : Intent =Intent(context,Activity2.class)



                        intent.putExtra("imageurl", list1.urlToImage)
                intent.putExtra("title",list1.title)
                intent.putExtra("desc",list1.description)

                intent.setFlags(intent.flags)
                context.startActivity(intent);



            }

        }
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData( List2 : List<Article>)
    {
        myList=List2
        notifyDataSetChanged()
    }



}
