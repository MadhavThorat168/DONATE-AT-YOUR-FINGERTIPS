package com.example.donation_app

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.*

class fooddetails : AppCompatActivity() {

    private var name: String? = null
    private var material: String? = null
    private var manifacute: String? = null
    private var origin: String? = null
    private var weight: String? = null
    private var url: String? = null

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)



        val txtproname = findViewById<TextView>(R.id.txtname)
        val txtmaterial = findViewById<TextView>(R.id.txtmaterial)
        val txtaddress = findViewById<TextView>(R.id.txtmanifacture)
        val txtarea = findViewById<TextView>(R.id.txtorigin)
        val txtwight = findViewById<TextView>(R.id.txtweigth)
        val image = findViewById<ImageView>(R.id.image1)

        val bundle = intent.extras
        name = bundle?.getString("proname")
        material = bundle?.getString("material")
        manifacute = bundle?.getString("manifacture")
        origin = bundle?.getString("authorname")
        weight = bundle?.getString("qty")
        url = bundle?.getString("url")

        Glide.with(this@fooddetails).load(url).into(image)

        txtproname.text = "Donor Name: $name"
        txtmaterial.text = "Address: $material"
        txtaddress.text = "Number: $manifacute"
        txtarea.text = "Item: $origin"
        txtwight.text = "qty: $weight"

        databaseReference = FirebaseDatabase.getInstance().getReference("food")

        val btntrack = findViewById<Button>(R.id.btnorder)
        val btnsend = findViewById<Button>(R.id.btntrack)
        val btncall = findViewById<Button>(R.id.btncall)
        val btnnoti = findViewById<Button>(R.id.btnnoti)

        btntrack.setOnClickListener {
            try {
                val uri = Uri.parse("https://www.google.co.in/maps/dir/$material")

                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

            } catch (e: ActivityNotFoundException) {
                val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }

        btnsend.setOnClickListener {
            Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
            val mDatabaseRef = databaseReference.orderByChild("name").equalTo(name)
            mDatabaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (obj in snapshot.children) {
                        val key = obj.key
                        databaseReference.child(key!!).child("status").setValue("Deactive")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                }
            })

            val smsManager = SmsManager.getDefault() as SmsManager
            smsManager.sendTextMessage("+91$manifacute", null, "New Request", null, null)
        }

        btncall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$manifacute")
            startActivity(intent)
        }

        btnnoti.setOnClickListener {
            val intent = Intent(applicationContext, order::class.java)
            intent.putExtra("name", name)
            intent.putExtra("number", material)
            intent.putExtra("address", manifacute)
            intent.putExtra("authorname", origin)
            intent.putExtra("qty", weight)
            startActivity(intent)
        }
    }
}
