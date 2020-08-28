package com.londriin

object CucianData {
    private val jenisCucian = arrayOf(
        "Cuci Baju",
        "Cuci Batik",
        "Cuci Selimut",
        "Cuci Seperai",
        "Cuci Sepatu",
        "Cuci Karpet",
        "Cuci Jas"
    )

    val listData: ArrayList<Cucian>
        get() {
            val list = arrayListOf<Cucian>()
            for (position in jenisCucian.indices) {
                val temp = Cucian()
                temp.jenis = jenisCucian[position]
                list.add(temp)
            }
            return list
        }

}