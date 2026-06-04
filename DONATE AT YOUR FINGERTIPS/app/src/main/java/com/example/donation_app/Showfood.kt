package com.example.donation_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener





class Showfood : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Foodadapter
    private lateinit var foodList: ArrayList<food>

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showfood)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        foodList = ArrayList()
        adapter = Foodadapter(foodList, this)
        recyclerView.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().getReference("food")
        fetchfoodItems()
    }

    private fun fetchfoodItems() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                foodList.clear()
                for (snapshot in dataSnapshot.children) {
                    val food = snapshot.getValue(food::class.java)
                    food?.let { foodList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
                Toast.makeText(this@Showfood, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
