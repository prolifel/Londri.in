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
import kotlinx.android.synthetic.main.activity_jenis_laundry.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.btn_back


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

        btn_back.setOnClickListener(this)

        auth = Firebase.auth
        database = Firebase.database.reference
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_daftar -> {
                auth.createUserWithEmailAndPassword(et_email.text.toString(), et_pass.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser

                            // add another user's information
                            writeNewUser(user!!.uid, et_nama.text.toString(), et_email.text.toString(), et_alamat.text.toString())
                            updateUI(user)
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this.baseContext, "Sign Up gagal", Toast.LENGTH_LONG).show()
                            updateUI(null)
                        }
                    }
            }

            R.id.btn_back -> {
                finish()
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
        database.child("users").child(userId).setValue(user)
    }
}