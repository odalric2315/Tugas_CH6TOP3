package com.percobaan.tugas_ch6top3.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int?,

    @ColumnInfo(name = "nama barang")
    val nama_barang : String,

    @ColumnInfo(name = "jumlah")
    val jumlah : String,

    @ColumnInfo(name = "warna")
    val warna : String

)