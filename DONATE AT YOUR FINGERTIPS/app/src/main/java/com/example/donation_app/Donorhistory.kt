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
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener





class Donorhistory : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DhistoryAdapter
    private lateinit var historyList: ArrayList<History>

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donorhistory)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        historyList = ArrayList()
        adapter = DhistoryAdapter(historyList,this)
        recyclerView.adapter = adapter

        val pref = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE)
        val donorName = pref.getString("name", "")

        databaseReference = FirebaseDatabase.getInstance().getReference("history")

        val query: Query = databaseReference.orderByChild("dname").equalTo(donorName)
        fetchbookItems()
    }

    private fun fetchbookItems() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                historyList.clear()
                for (snapshot in dataSnapshot.children) {
                    val History = snapshot.getValue(History::class.java)
                    History?.let { historyList.add(it) }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
                Toast.makeText(this@Donorhistory, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
