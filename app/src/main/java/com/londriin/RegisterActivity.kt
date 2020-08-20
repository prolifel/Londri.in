package com.londriin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthMultiFactorException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    companion object {
        private const val TAG = "EmailPassword"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister = findViewById<Button>(R.id.btn_daftar)
        btnRegister.setOnClickListener(this)

        auth = Firebase.auth
        database = Firebase.database.reference
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_daftar -> {
                val email = findViewById<EditText>(R.id.et_email)
                val password = findViewById<EditText>(R.id.et_pass)
                val nama = findViewById<EditText>(R.id.et_nama)
                val alamat = findViewById<EditText>(R.id.et_alamat)

                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            writeNewUser(user!!.uid, nama.text.toString(), email.text.toString(), alamat.text.toString())
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }

                    }
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            val intent = Intent(this@RegisterActivity, HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun writeNewUser(userId: String, nama: String, email: String?, alamat:String?) {
        val user = User(nama, email, alamat)
        database.child("londri-in").child(userId).setValue(user)
    }
}