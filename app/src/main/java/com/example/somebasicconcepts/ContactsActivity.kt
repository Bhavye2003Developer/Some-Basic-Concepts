package com.example.somebasicconcepts

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import com.example.somebasicconcepts.databinding.ActivityContactsBinding
import com.example.somebasicconcepts.databinding.ActivityMainBinding

class ContactsActivity : AppCompatActivity() {

    lateinit var binding : ActivityContactsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_contacts)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectContact.setOnClickListener {
            selectContact()
        }
    }
    companion object {
        const val REQUEST_SELECT_CONTACT = 1
    }
    fun selectContact() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = ContactsContract.Contacts.CONTENT_TYPE
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_CONTACT)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
            val contactUri: Uri? = data?.data
//            Toast.makeText(this,Uri.parse(contactUri.toString()).toString(),Toast.LENGTH_SHORT).show()
            val cursor = contactUri?.let { contentResolver.query(it, null, null, null, null) }
            cursor?.moveToFirst()
            // Extract the contact's name and phone number
            val nameIndex = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val name = if(nameIndex != -1) nameIndex?.let { cursor.getString(it) } else ""
            val phoneNumberIndex = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val phoneNumber = if(phoneNumberIndex != -1) phoneNumberIndex?.let { cursor.getString(it) }
            else ""
            cursor?.close()
            // Do something with the extracted information
//            Log.d("Contact", "Name: $name, Phone Number: $phoneNumber")
            if (phoneNumber==""){
//                Toast.makeText(this,phoneNumberIndex.toString(),Toast.LENGTH_SHORT).show()
                binding.result.text = "Name: $name"
            }
            else{
                binding.result.text = "Name: $name\nPhone Number: $phoneNumber"
            }
        }
    }
}