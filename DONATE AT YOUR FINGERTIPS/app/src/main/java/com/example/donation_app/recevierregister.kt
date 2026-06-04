package com.example.donation_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.donation_app.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class recevierregister : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val name = binding.Name
        val number = binding.number
        val email = binding.username
        val password = binding.password
        val address = binding.address
        val btn = binding.loginButton

        auth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("receiver")

        btn.setOnClickListener {

            val mail = email.text.toString()

            if(name.text.isEmpty())
            {
                name.setError("Enter name")
                return@setOnClickListener
            }else if(password.text.isEmpty())
            {
                password.setError("Enter Password ")
                return@setOnClickListener
            }else if(number.text.length>10)
            {
                number.setError("Enter 10 Digit Number")
                return@setOnClickListener
            }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches())
            {
                email.setError("Enter Email id")
                return@setOnClickListener
            }


            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        val currentuser = auth.currentUser
                        val currentUserdb = databaseReference?.child((currentuser?.uid!!))
                        currentUserdb?.child("name")?.setValue(name.text.toString())
                        currentUserdb?.child("address")?.setValue(address.text.toString())


                        currentUserdb?.child("number")?.setValue(number.text.toString())

                        Toast.makeText(applicationContext,"Registration Successfully", Toast.LENGTH_LONG).show()

                        sharedata(name.text.toString(),address.text.toString())
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"failed", Toast.LENGTH_LONG).show()
                    }
                }
        }

    }

    private fun sharedata(name: String, address: String) {

        val editor = getSharedPreferences("MY", MODE_PRIVATE).edit()

        editor.putString("name", name)
        editor.putString("address",address)

        editor.apply()
        editor.commit()

    }
}