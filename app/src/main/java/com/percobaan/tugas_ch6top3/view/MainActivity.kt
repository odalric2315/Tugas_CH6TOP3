package com.percobaan.tugas_ch6top3.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.percobaan.tugas_ch6top3.adapter.MyAdapter
import com.percobaan.tugas_ch6top3.databinding.ActivityMainBinding
import com.percobaan.tugas_ch6top3.databinding.EditDialogBinding
import com.percobaan.tugas_ch6top3.db.MyDataBase
import com.percobaan.tugas_ch6top3.model.Note
import com.percobaan.tugas_ch6top3.presenter.MainPresenterImp
import com.percobaan.tugas_ch6top3.util.MySharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myPref: MySharedPref
    private lateinit var adapter: MyAdapter
    private val notesData = mutableListOf<Note>()
    private var dataBase: MyDataBase? = null
    private lateinit var presenter: MainPresenterImp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MyAdapter(notesData)
        adapter.setOnClickListerner {
            showEditDialog(it)
        }
        adapter.setOnDeleteListerner {
            showConfirmationDeleteDialog(it)
        }

        val database = MyDataBase.getInstance(this)
        presenter  = MainPresenterImp(this, database.noteDao())
        presenter.getData()

        binding.rvNote.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            val note = Note(null,"meja", "1","coklat")
            presenter.addData(note)
            GlobalScope.launch (Dispatchers.Main) {
                val result = dataBase?.noteDao()?.addNote(note)
                runOnUiThread {
                    if(result !=0.toLong()){
                        //sukses
                        Toast.makeText(this@MainActivity,"Sukses menambahkan ${note.nama_barang}",
                            Toast.LENGTH_LONG).show()}
                    else{
                        //gagal
                        Toast.makeText(this@MainActivity,"Gagal menambahkan ${note.nama_barang}",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }


    fun showLoading() {
        GlobalScope.launch(Dispatchers.Main){
            binding.progressBar.visibility = View.VISIBLE
        }
    }
    fun hideLoading() {
        GlobalScope.launch(Dispatchers.Main) {
            binding.progressBar.visibility = View.GONE
        }
    }
    
    fun showAddData(data: List<Note>?) {
        GlobalScope.launch(Dispatchers.Main) {

            data?.let {
                notesData.clear()
                notesData.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
    }
    fun showConfirmationDeleteDialog(id: Int?) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete Confirmation")
            .setMessage("Are you sure delete this data?")
        dialog.setPositiveButton("Yes") { _, _ ->
            presenter.removeData(id!!)
        }
        dialog.setNegativeButton("No") { d, _ ->
            d.dismiss()
        }
        dialog.create().show()
    }

    private fun showEditDialog(note: Note) {
        val builder = AlertDialog.Builder(this)
        val view = EditDialogBinding.inflate(layoutInflater)
        builder.setView(view.root)
        val dialog = builder.create()
        view.etNamabarang.setText(note.nama_barang)
        view.etJumlah.setText(note.jumlah)
        view.etWarna.setText(note.warna)
        view.btnEditCancel.setOnClickListener {
            dialog.dismiss()
        }
        view.btnSave.setOnClickListener {
            val nama_barang = view.etNamabarang.text.toString()
            val jumlah = view.etJumlah.text.toString()
            val warna = view.etWarna.text.toString()
            val note = Note(note.id, nama_barang, jumlah, warna)
            presenter.updateData(note)
            dialog.dismiss()
        }
        dialog.show()
    }

}
