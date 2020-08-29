package com.londriin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_jenis_laundry.*

class HomeScreenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    companion object {
        private const val TAG = "User"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val btnLoggOut = findViewById<Button>(R.id.btn_log_out)
        btnLoggOut.setOnClickListener(this)

        btn_laundry.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_log_out -> {
                auth.signOut()
                val intent = Intent(this@HomeScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            R.id.btn_laundry -> {
                val intent = Intent(this@HomeScreenActivity, JenisLaundryActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        auth = Firebase.auth

        database = Firebase.database.getReference("users").child(auth.uid!!)

        // reading data user
        val userListener = object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "loadUser:onCancelled", error.toException())
                Toast.makeText(baseContext, "Gagal reading data",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue<User>()
                tv_user.text = user?.nama

            }
        }

        database.addListenerForSingleValueEvent(userListener)
    }
}