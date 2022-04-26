package com.example.hongappdetector

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.hongappdetector.databinding.ActivityMainBinding
import com.example.hongappdetector.ActivityViwerFromGallery as ActivityViwerFromGallery1

class MainActivity : AppCompatActivity() {
    private var numBoton: Int = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttoncamera.setOnClickListener { checkPermissionApp(1) }
        binding.buttongaleria.setOnClickListener { checkPermissionApp(2) }
    }

    private fun checkPermissionApp(boton: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(boton == 1){
                numBoton = 1
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    requestOpernCamera()
                }
                else{
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
            else{
                numBoton = 2
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    requestOpenStorage()
                }
                else{
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }
        }
        else{
            if (boton == 1){
                requestOpernCamera()
            }
            else{
                requestOpenStorage()
            }
        }
    }


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ){
        result -> if(result){
            if (numBoton == 1){
                requestOpernCamera()
            }
            else{
                requestOpenStorage()
            }
        }
    }

    private fun requestOpernCamera() {
        Toast.makeText(this, "Cámara", Toast.LENGTH_SHORT).show()
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result -> if (result.resultCode == Activity.RESULT_OK){
            val data = result.data?.data
            val viewer = Intent(this, ActivityViwerFromGallery1::class.java)
            viewer.putExtra("image", data.toString())
            startActivity(viewer)
        }
    }


    private fun requestOpenStorage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startForActivityGallery.launch(intent)
    }


}