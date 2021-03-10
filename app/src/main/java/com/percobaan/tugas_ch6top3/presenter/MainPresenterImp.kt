package com.percobaan.tugas_ch6top3.presenter

import com.percobaan.tugas_ch6top3.db.NoteDao
import com.percobaan.tugas_ch6top3.model.Note
import com.percobaan.tugas_ch6top3.view.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenterImp(private val view: MainActivity, private val noteDao: NoteDao) : MainPresenter {
    override fun addData(note: Note) {
        GlobalScope.launch {
            noteDao.addNote(note)
            getData()
        }
    }

    override fun removeData(index: Int) {
        GlobalScope.launch {
            noteDao.deleteNote(index)
            getData()
        }
    }

    override fun getData() {
        view.showLoading()
        GlobalScope.launch {
            val notes = noteDao.getNotes()
            view.showAddData(notes)
        }
        view.hideLoading()
    }

    override fun updateData(note: Note) {
        GlobalScope.launch {
            noteDao.updateNote(note)
            getData()
        }
    }
}
