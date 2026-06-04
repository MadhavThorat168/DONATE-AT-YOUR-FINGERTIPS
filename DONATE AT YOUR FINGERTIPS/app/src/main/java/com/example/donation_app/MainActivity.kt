package com.example.donation_app


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.donation_app.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    lateinit var auth : FirebaseAuth
    val dropDownList = arrayOf("Select User Type\n","Donor","Receiver")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edemail = binding.username
        val edpassword = binding.password

        val btnlogin = binding.loginButton
        val btnregister = binding.signupText

        val statusFillter = findViewById<Spinner>(R.id.spinner)

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,dropDownList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        statusFillter.adapter = adapter

        statusFillter.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(statusFillter.selectedItemPosition==1)
                {
                    Toast.makeText(applicationContext,statusFillter.selectedItem.toString(), Toast.LENGTH_LONG).show()

                }else if(statusFillter.selectedItemPosition==2)
                {
                    Toast.makeText(applicationContext,statusFillter.selectedItem.toString(), Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext, receriverlogin::class.java)
                    startActivity(intent)
                }
            }



            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }



        }


        btnregister.setOnClickListener {
            val intent = Intent(applicationContext, Register::class.java)
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
                        val intent = Intent(applicationContext, Donordash::class.java)
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