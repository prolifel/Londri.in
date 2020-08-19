package com.londriin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                val intent = Intent(this@MainActivity, HomeScreenActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


}