package com.londriin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.request.RequestOptions

class GridMitraAdapter(val listMitra: ArrayList<Mitra>) : RecyclerView.Adapter<GridMitraAdapter.GridViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class GridViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var iconMitra: ImageView = itemView.findViewById(R.id.icon_mitra)
        var textMitra: TextView = itemView.findViewById((R.id.tv_mitra))
        var rbMitra: RatingBar = itemView.findViewById(R.id.rb_mitra)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_mitra, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMitra.size
    }

    override fun onBindViewHolder(holder: GridMitraAdapter.GridViewHolder, position: Int) {
        val mitra = listMitra[position]

        Glide.with(holder.itemView.context)
            .load(mitra.iconURL)
            .into(holder.iconMitra)

        holder.textMitra.text = mitra.nama
        holder.rbMitra.rating = mitra.avgrating.toFloat()

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMitra[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Mitra)
    }
}