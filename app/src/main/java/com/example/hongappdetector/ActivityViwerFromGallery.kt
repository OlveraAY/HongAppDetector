package com.example.hongappdetector

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hongappdetector.databinding.ActivityViwerFromGalleryBinding

class ActivityViwerFromGallery : AppCompatActivity() {
    private lateinit var binding: ActivityViwerFromGalleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViwerFromGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataString = intent.getStringExtra("image")
        val imageString = Uri.parse(dataString)
        binding.imageView4.setImageURI(imageString)
        binding.buttonHome.setOnClickListener { onBackPressed() }


    }
}