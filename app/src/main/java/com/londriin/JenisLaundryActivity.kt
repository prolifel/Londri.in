package com.londriin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_jenis_laundry.btn_back

class JenisLaundryActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvJenis: RecyclerView
    private var list: ArrayList<Cucian> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jenis_laundry)

        // find recycleview
        rvJenis = findViewById(R.id.rv_jenis_laundry)
        rvJenis.setHasFixedSize(true)

        // show recycleview
        list.addAll(CucianData.listData)
        showRecyclerList()

        // back listener
        btn_back.setOnClickListener(this)
    }

    private fun showRecyclerList() {
        // show recycle
        rvJenis.layoutManager = LinearLayoutManager(this)
        val listJenisLaundryAdapter = CardViewCucianAdapter(list)
        rvJenis.adapter = listJenisLaundryAdapter

        // if selected
        listJenisLaundryAdapter.setOnItemClickCallback(object : CardViewCucianAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Cucian) {
                showSelectedType(data)
            }
        })
    }

    // intent to pilih mitra
    private fun showSelectedType(data: Cucian) {
        val intent = Intent(this@JenisLaundryActivity, PilihMitraActivity::class.java)
        intent.putExtra(PilihMitraActivity.JENIS_LAUNDRY, data.jenis)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_back -> {
                finish()
            }
        }
    }
}