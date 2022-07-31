package com.techmania.springr.View

import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.getBitmap
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.squareup.picasso.Picasso
import com.techmania.springr.R

import com.techmania.springr.databinding.Activity2Binding
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime.*
import java.util.*

class Activity2 : AppCompatActivity() {
    lateinit var binding2: Activity2Binding
    lateinit var bt1: Button
    lateinit var img: ImageView
    lateinit var sharebtn: Button
    lateinit var detail: TextView
    lateinit var title: TextView
    lateinit var urlImage: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding2 = Activity2Binding.inflate(layoutInflater)
        val view = binding2.getRoot()
        setContentView(binding2.root)


        binding2.dateShow.text = SimpleDateFormat("EEEE, MMMM dd, yyyy").format(Date())
        bt1 = binding2.save
        img = binding2.secondImage
        sharebtn = binding2.share
        detail = binding2.DetailNews
        title = binding2.Title
        urlImage=binding2.imageUrl
// getting data from intent

       urlImage.setText(getIntent().getStringExtra("imageurl"))
       // img.setImageResource(getIntent().getIntExtra("imageurl", 0))
        detail.setText(getIntent().getStringExtra("desc"))
        title.setText(getIntent().getStringExtra("title"))


        Picasso.get().load(urlImage).into(img)


        var drawable1: BitmapDrawable = img.drawable as BitmapDrawable
        var bitmap: Bitmap = drawable1.bitmap

// save button clicked image will be stored in internal storage
        bt1.setOnClickListener {


            val retn: Boolean = savePhotoToInternalStorage(UUID.randomUUID().toString(), bitmap)
            if (retn) {

                Toast.makeText(this, "Photo saved successfully", Toast.LENGTH_SHORT).show()


            }



            sharebtn.setOnClickListener {


                val b = BitmapFactory.decodeResource(resources, img as Int)
                val intent = Intent()
                intent.action = ACTION_SEND
                val path = MediaStore.Images.Media.insertImage(contentResolver, b, "Title", null)
                val uri = Uri.parse(path)
                intent.putExtra(Intent.EXTRA_STREAM, uri)
                intent.type = "image/*"
                startActivity(Intent.createChooser(intent, "Share To:"))

            }


        }
    }


    fun savePhotoToInternalStorage(filename: String, bmp: Bitmap): Boolean {

        return try {

            openFileOutput("$filename.jpg", MODE_PRIVATE).use { stream ->
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                    throw IOException("couldnot save bitmap")
                }
            }
            true


        } catch (e: IOException) {

            e.printStackTrace()
            false
        }


    }
}