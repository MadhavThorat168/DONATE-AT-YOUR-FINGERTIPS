package com.example.donation_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class order : AppCompatActivity() {

    private var edname: EditText? = null
    private var edprofile: EditText? = null
    private var edcriteria: EditText? = null

    private var name: String? = null
    private var material: String? = null
    private var manufacture: String? = null
    private var origin: String? = null
    private var weight: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        edname = findViewById(R.id.edtype)
        edprofile = findViewById(R.id.edname)
        edcriteria = findViewById(R.id.eddes)

        val bundle = intent.extras
        name = bundle?.getString("name")
        material = bundle?.getString("material")
        manufacture = bundle?.getString("manufacture") // Corrected variable name
        origin = bundle?.getString("authorname") // Corrected variable name
        weight = bundle?.getString("qty") // Corrected variable name

        Toast.makeText(applicationContext, name, Toast.LENGTH_LONG).show()
    }

    fun UploadData(view: View?) {
        val address = edname?.text.toString()
        val rname = edprofile?.text.toString()
        val number = edcriteria?.text.toString()

        if (address.isNullOrEmpty() || rname.isNullOrEmpty() || number.isNullOrEmpty()) {
            
            Toast.makeText(applicationContext, "Please fill in all fields", Toast.LENGTH_LONG).show()
            return
        }

        val data = FirebaseDatabase.getInstance().reference.child("history")
        val service = History(name, material, manufacture, weight, origin, address, rname, number)

        data.push().setValue(service)

        // Clear EditText fields after successful upload
        edname?.setText("")
        edprofile?.setText("")
        edcriteria?.setText("")

        // Navigate to the History activity after data has been uploaded
        val intent = Intent(this, Recevierhistory::class.java)
        startActivity(intent)

        Toast.makeText(applicationContext, "Uploaded", Toast.LENGTH_LONG).show()
    }
}
