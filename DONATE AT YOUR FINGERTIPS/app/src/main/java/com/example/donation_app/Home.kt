package com.example.donation_app


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.donation_app.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val img1 = binding.je
        val img2 = binding.cloth

        val img3 = binding.sport





        img1.setOnClickListener {
            val i = Intent(applicationContext, Booklocation::class.java)
            startActivity(i)
        }
        img2.setOnClickListener {
           // Toast.makeText(applicationContext,"Cloth",Toast.LENGTH_LONG).show()

            val i = Intent(applicationContext, clothlocation::class.java)
            startActivity(i)
        }

        img3.setOnClickListener {
            val i = Intent(applicationContext, Foodlocation::class.java)
            startActivity(i)
        }




        val bottom = findViewById<BottomNavigationView>(R.id.bottom)




        bottom.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {


                R.id.shop ->
                {
                    Toast.makeText(applicationContext,"Add Book",Toast.LENGTH_LONG).show()

                    val i = Intent(applicationContext, Addbook::class.java)
                    startActivity(i)
                    true

                }


                R.id.feedback ->
                {

                    Toast.makeText(applicationContext,"Cloth",Toast.LENGTH_LONG).show()
                    val i = Intent(applicationContext, Addcloth::class.java)
                    startActivity(i)
                    true
                }
                R.id.showre ->
                {

                    Toast.makeText(applicationContext,"Food",Toast.LENGTH_LONG).show()
                    val i = Intent(applicationContext, Addfood::class.java)
                    startActivity(i)
                    true
                }



                else -> {false}
            }
        }
    }
}