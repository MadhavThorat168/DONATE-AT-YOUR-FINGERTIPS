package com.example.donation_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.donation_app.databinding.ActivityReceriverloginBinding
import com.google.firebase.auth.FirebaseAuth

class receriverlogin : AppCompatActivity() {

    private lateinit var binding: ActivityReceriverloginBinding
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceriverloginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edemail = binding.username
        val edpassword = binding.password

        val btnlogin = binding.loginButton
        val btnregister = binding.signupText




        btnregister.setOnClickListener {
            val intent = Intent(applicationContext, recevierregister::class.java)
            startActivity(intent)

        }

        auth = FirebaseAuth.getInstance()

        //login
        btnlogin.setOnClickListener {

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(edemail.text.toString()).matches()) {
                edemail.setError("Enter Email Id")
                return@setOnClickListener
            }
            else if (edpassword.text.isEmpty()){
                edpassword.setError("Enter Password")
                return@setOnClickListener
            }



            auth.signInWithEmailAndPassword(edemail.text.toString(),edpassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"successfully Login", Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, RecevierDash::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Failed to login", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}