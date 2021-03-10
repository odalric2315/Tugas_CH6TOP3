package com.percobaan.tugas_ch6top3.util

import android.content.Context

class MySharedPref(context: Context) {
    private val sharedPref = context.getSharedPreferences("Mypref", Context.MODE_PRIVATE)
    companion object{
        const val NAMA_BARANG = "nama barang"
        const val JUMLAH_BARANG = "jumlah barang"
        const val WARNA = "warna"
    }
    var name : String?
        get() {
            return sharedPref.getString(NAMA_BARANG, "No Data")
        }
        set(value) {
            sharedPref.edit().putString(NAMA_BARANG,value).apply()
        }

    var jumlah_barang : String?
        get() {
            return sharedPref.getString(JUMLAH_BARANG,"No Data")
        }
        set(value) {
            sharedPref.edit().putString(JUMLAH_BARANG,value).apply()
        }

    var warna : String?
        get() {
            return sharedPref.getString(WARNA,"No Data")
        }
        set(value) {
            sharedPref.edit().putString(WARNA,value).apply()
        }

    fun removeSharePref(key: String){
        sharedPref.edit().remove(key).apply()
    }

    fun clearSharePref(){
        sharedPref.edit().clear().apply()
    }
}