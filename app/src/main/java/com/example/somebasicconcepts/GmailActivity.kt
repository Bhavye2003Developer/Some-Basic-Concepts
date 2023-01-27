package com.example.somebasicconcepts

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.example.somebasicconcepts.databinding.ActivityGmailBinding
import com.example.somebasicconcepts.databinding.ActivityMainBinding

class GmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_gmail)
        binding = ActivityGmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mail.setOnClickListener {
            val text : String ?= binding.gmailText.editableText?.toString()
            val EmailAddress : String = binding.mailAddress.editableText?.toString() ?: ""
            if (EmailAddress==""){
                Toast.makeText(this,"Please Enter Email Address",Toast.LENGTH_SHORT).show()
            }
            else {
                Intent(Intent.ACTION_SEND).also {
                    it.type = "text/plain"
//                val data = Uri.parse("mailto:") // only email apps should handle this
                    it.putExtra(Intent.EXTRA_EMAIL, text)
                    it.putExtra(Intent.EXTRA_SUBJECT, "Important")
                    it.putExtra(Intent.EXTRA_TEXT, text)
                    if (it.resolveActivity(packageManager) != null) startActivity(it)
                    else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}