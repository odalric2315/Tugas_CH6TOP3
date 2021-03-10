package com.percobaan.tugas_ch6top3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.percobaan.tugas_ch6top3.model.Note

@Database(entities = [Note::class],version = 2,exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: MyDataBase? = null
        fun getInstance(context: Context): MyDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDataBase::class.java,
                    "my_note_ch6_top3"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}