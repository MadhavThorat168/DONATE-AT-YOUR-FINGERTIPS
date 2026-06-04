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





class Recevierhistory : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RhistoryAdapter
    private lateinit var historyList: ArrayList<History>

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recevierhistory)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        historyList = ArrayList()
        adapter = RhistoryAdapter(historyList,this)
        recyclerView.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().getReference("history")
        fetchhistoryItems()
    }

    private fun fetchhistoryItems() {
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
                Toast.makeText(this@Recevierhistory, "Database Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
