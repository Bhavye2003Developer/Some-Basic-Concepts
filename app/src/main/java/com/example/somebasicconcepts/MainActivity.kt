package com.example.somebasicconcepts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.somebasicconcepts.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraBtn.setOnClickListener {
            Toast.makeText(this,"Opening Camera Activity...",Toast.LENGTH_SHORT).show()
            Intent(this,Camera_Activity::class.java).also {
                startActivity(it)
            }
        }
        binding.gmailBtn.setOnClickListener {
            Toast.makeText(this,"Opening gmail Activity...",Toast.LENGTH_SHORT).show()
            Intent(this,GmailActivity::class.java).also{
                startActivity(it)
            }
        }
        binding.contact.setOnClickListener {
            Toast.makeText(this,"Opening Contacts Activity...",Toast.LENGTH_SHORT).show()
            Intent(this,ContactsActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}