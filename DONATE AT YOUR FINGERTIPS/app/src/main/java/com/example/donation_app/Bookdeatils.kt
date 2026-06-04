package com.example.donation_app

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.donation_app.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Bookdeatils : AppCompatActivity() {

    var name: String? = null
    var material: String? = null
    var manifacute: String? = null
    var origin: String? = null
    var weight: String? = null
    var rating: String? = null
    var demi: String? = null
    var price: String? = null

    var ref: DatabaseReference? = null
    var username: String? = null
    var usermobile: String? = null
    var useremail: String? = null
    var useraddress: String? = null
    var url: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookdeatils)

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

        Glide.with(this@Bookdeatils).load(url).into(image)

        txtproname.setText("Donor Name:" + name)
        txtmaterial.setText("Address: " + material)
        txtaddress.setText("Number: " + manifacute )
        txtarea.setText("Item: " + origin)
        txtwight.setText("qty: " + weight)

//        txtprice.setText("Price: " + price)


        val btntrack = findViewById<Button>(R.id.btnorder)

        btntrack.setOnClickListener {
            try {
                val uri = Uri.parse("https://www.google.co.in/maps/dir/"+"/"+ material)

                val intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }catch (e: ActivityNotFoundException)
            {
                val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                val intent = Intent(Intent.ACTION_VIEW,uri)
                intent.setPackage("com.google.android.apps.maps")
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }



        val  btnsend = findViewById<Button>(R.id.btntrack)

        btnsend.setOnClickListener {
            Toast.makeText(applicationContext,name,Toast.LENGTH_LONG).show()

            val mDatabaseRef = FirebaseDatabase.getInstance().getReference("book").orderByChild("name").equalTo(name)
            mDatabaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
//                println(snapshot)
                    for(obj in snapshot.children)
                    {
//                   println(obj)
                        println(obj.key)

                        val key = obj.key

                        val databaseReference = FirebaseDatabase.getInstance().getReference("book").child(key!!)
                        databaseReference.child("status").setValue("Deactive")
                        databaseReference.child("name").setValue(name)
                        databaseReference.child("number").setValue(manifacute)
                        databaseReference.child("address").setValue(material)
                        databaseReference.child("book").setValue(origin)
                        databaseReference.child("qty").setValue(weight)


                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
            val smsManager = SmsManager.getDefault() as SmsManager
            smsManager.sendTextMessage("+91$manifacute",null,"New Request",null,null)
        }

        val btncall = findViewById<Button>(R.id.btncall)

        btncall.setOnClickListener {

            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$manifacute")

            startActivity(intent)


        }

        val btnnoti = findViewById<Button>(R.id.btnnoti)

        btnnoti.setOnClickListener {


            val intent = Intent(applicationContext,order::class.java)
            intent.putExtra("name", name )
            intent.putExtra("number", material)
            intent.putExtra("address", manifacute)
            intent.putExtra("authorname", origin)
            intent.putExtra("qty",weight)
            startActivity(intent)



        }
    }
}