package com.londriin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Comment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    companion object {
        private const val TAG = "EmailPassword"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        // add button daftar sekarang
        val btnDaftar = findViewById<TextView>(R.id.tv_daftar_sekarang)
        btnDaftar.setOnClickListener(this)

        // add button login
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            // intent kalau klik daftar sekarang
            R.id.tv_daftar_sekarang -> {
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            // intent kalau klik login
            R.id.btn_login -> {
                if (input_email.text?.isEmpty()!! || input_pass.text?.isEmpty()!!)
                    Toast.makeText(applicationContext, "Email dan password harus diisi", Toast.LENGTH_SHORT).show()
                else{
                    auth.signInWithEmailAndPassword(input_email.text.toString(), input_pass.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "signInWithEmail:success")
                                val user = auth.currentUser
                                updateUI(user)
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Log in gagal. Harap masukkan email dan password yang benar", Toast.LENGTH_SHORT).show()
                                updateUI(null)
                            }
                        }
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            val intent = Intent(this@MainActivity, HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }else{

        }
    }

}