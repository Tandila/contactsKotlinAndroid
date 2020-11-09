package com.cst.contacts.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cst.contacts.R

/**
 * ამ ექთივითის უნდა გადმოაწოდოთ კონტაქტის ID,
 * რომელიც თავის მხრივ გადააწვდის ამ ID-ს ფრაგმენტს
 */
class ContactDetailedActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_contact)

        val id = intent.getLongExtra("id", 1)
        val result = ContactDetailedFragment().getContactById(id)

        findViewById<TextView>(R.id.firstNameLetterId).apply {
            text = result?.name?.substring(0,1)
        }
        findViewById<TextView>(R.id.nameId).apply {
            text = result?.name
        }
        findViewById<TextView>(R.id.phoneNumberId).apply {
            text = result?.phoneNumbers?.get(0)?.number
        }
        findViewById<TextView>(R.id.phoneNumberType).apply {
            text = result?.phoneNumbers?.get(0)?.type.toString()
        }
        findViewById<TextView>(R.id.emailId).apply {
            text = result?.emails?.get(0)?.address
        }
        findViewById<TextView>(R.id.emailType).apply {
            text = result?.emails?.get(0)?.type.toString()
        }
    }

    fun back(view: View) {
        onBackPressed()
    }

    fun call(view: View) {
        Toast.makeText(this, "Calling", Toast.LENGTH_SHORT).show()
    }
    fun message(view: View) {
        Toast.makeText(this, "Messaging", Toast.LENGTH_SHORT).show()
    }
    fun email(view: View) {
        Toast.makeText(this, "Emailing", Toast.LENGTH_SHORT).show()
    }
    fun videoCall(view: View) {
        Toast.makeText(this, "Video Calling", Toast.LENGTH_SHORT).show()
    }

}