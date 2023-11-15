package com.vicmoy.firebasewithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    private val TAG = "MITAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Victor"
        user["last"] = "Hernández"
        user["born"] = 1994

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapShot Added with ID: $documentReference" )
            }
            .addOnFailureListener {e ->
                Log.d(TAG, "Error adding document: $e")
            }

    }


}