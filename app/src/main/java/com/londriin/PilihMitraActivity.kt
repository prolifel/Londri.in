package com.londriin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_pilih_mitra.*

class PilihMitraActivity : AppCompatActivity() {
    private lateinit var rvMitra : RecyclerView
    private var list: ArrayList<Mitra> = arrayListOf()
    private lateinit var database: DatabaseReference
    private lateinit var temp: GridMitraAdapter

    companion object {
        private const val TAG = "User"
        const val JENIS_LAUNDRY = "jenis_laundry"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_mitra)

        // display Jenis Cucian
        tv_nama_jenis.text = intent.getStringExtra(JENIS_LAUNDRY)

        // initiate db retrieve data mitra
        database = Firebase.database.getReference("mitra")

        // define recycleview
        rvMitra = findViewById(R.id.rv_mitra)
        rvMitra.setHasFixedSize(true)
        rvMitra.layoutManager = GridLayoutManager(this, 2)

        // reading data Mitra from db
        val userListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.w(PilihMitraActivity.TAG, "loadDataMitra:onCancelled", error.toException())
                Toast.makeText(baseContext, "Gagal reading data",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (npsnapshot in snapshot.children) {
                        val count = npsnapshot.childrenCount.toInt()
                        Log.d("count",count.toString())
                        Log.d("check", npsnapshot.child("nama").value.toString())

                        val item = npsnapshot.getValue(Mitra::class.java)
                        list.add(item!!)
                    }
                }
                // recycleview show
                val listJenisLaundryAdapter = GridMitraAdapter(list)
                rvMitra.adapter = listJenisLaundryAdapter

                // if item selected
                listJenisLaundryAdapter.setOnItemClickCallback(object : GridMitraAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Mitra) {
                        showSelectedType(data)
                    }
                })
            }
        }

        database.addValueEventListener(userListener)
    }

    private fun showSelectedType(data: Mitra) {
        Log.d(TAG, "cokcok")
        Toast.makeText(applicationContext, "cok", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@PilihMitraActivity, PenjemputanActivity::class.java)
        startActivity(intent)
    }
}