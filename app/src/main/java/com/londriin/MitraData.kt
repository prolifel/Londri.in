package com.londriin

object MitraData {
    private val jenisMitra = arrayOf(
        "Cuci Baju",
        "Cuci Batik",
        "Cuci Selimut",
        "Cuci Seperai"
    )

    val listData: ArrayList<Mitra>
        get() {
            val list = arrayListOf<Mitra>()
            for (position in jenisMitra.indices) {
                val temp = Mitra()
                temp.nama = jenisMitra[position]
                list.add(temp)
            }
            return list
        }

}