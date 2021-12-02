package com.pankajkcodes.memesappkotlin

import android.app.ProgressDialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    private lateinit var img: ImageView
    private lateinit var nextBtn: Button
    private lateinit var shareBtn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()

        img = findViewById(R.id.imageView)
        nextBtn = findViewById(R.id.nextBtn)
        shareBtn = findViewById(R.id.shareBtn)




        nextBtn.setOnClickListener {
            loadMeme()
        }

        shareBtn.setOnClickListener {

        }

    }

    private fun loadMeme() {
        // Instantiate the RequestQueue.

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url,
            null, { response ->

                val url = response.getString("url")

                Glide.with(this)
                    .load(url).listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {

                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {

                            return false
                        }

                    })
                    .into(img)

            }, { error ->

                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }
}