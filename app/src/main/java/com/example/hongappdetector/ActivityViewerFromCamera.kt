package com.example.hongappdetector

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hongappdetector.databinding.ActivityViewerFromCameraBinding

class ActivityViewerFromCamera : AppCompatActivity() {
    private lateinit var binding: ActivityViewerFromCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityViewerFromCameraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val imageBundle = intent.getBundleExtra("image")
        val imageBitmap = imageBundle?.get("data") as Bitmap
        binding.imageView4.setImageBitmap(imageBitmap)
        binding.buttonHome.setOnClickListener { onBackPressed() }
    }
}