package com.londriin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeScreenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        auth = Firebase.auth

        val btnLoggOut = findViewById<Button>(R.id.btn_log_out)
        btnLoggOut.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_log_out -> {
                auth.signOut()
                val intent = Intent(this@HomeScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


}