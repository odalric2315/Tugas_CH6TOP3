package com.percobaan.tugas_ch6top3.presenter

import com.percobaan.tugas_ch6top3.model.Note

interface MainPresenter {
    fun addData(note: Note)
    fun removeData (index: Int)
    fun getData()
    fun updateData(note: Note)
}