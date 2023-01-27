package com.example.somebasicconcepts

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.somebasicconcepts.databinding.ActivityCameraBinding
import com.example.somebasicconcepts.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class Camera_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_camera)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startCamera.setOnClickListener {
            val cameraIntent =
                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
    companion object {
        const val REQUEST_IMAGE_CAPTURE = 123
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            val extras : Bundle? = data?.extras
            val photo = extras?.get("data") as Bitmap
            // Set the image in imageview for display
            binding.imageView.setImageBitmap(photo)
        }
    }
}