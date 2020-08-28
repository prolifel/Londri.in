package com.londriin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardViewCucianAdapter(val listTipe: ArrayList<Cucian>) : RecyclerView.Adapter<CardViewCucianAdapter.CardViewViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_jenis_laundry, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val tipe = listTipe[position]

        // change button text & listener on click
        holder.tvName.text = tipe.jenis
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTipe[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listTipe.size
    }

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.btn_jenis_laundry)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Cucian)
    }
}