package com.example.donation_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Donordash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donordash)

        // Find the ImageButtons
        val button1 = findViewById<ImageButton>(R.id.book)
        val button2 = findViewById<ImageButton>(R.id.cloth)
        val button3 = findViewById<ImageButton>(R.id.food)
        val button4 = findViewById<ImageButton>(R.id.history)

        // Set click listeners for each button
        button1.setOnClickListener {
            val i = Intent(applicationContext, Addcloth::class.java)
            startActivity(i)
            true
        }

        button2.setOnClickListener {
            val i = Intent(applicationContext, Addbook::class.java)
            startActivity(i)
            true
        }

        button3.setOnClickListener {
            val i = Intent(applicationContext, Addfood::class.java)
            startActivity(i)
            true
        }

        button4.setOnClickListener {
            val i = Intent(applicationContext, Donorhistory::class.java)
            startActivity(i)
            true
        }


        }
    }

