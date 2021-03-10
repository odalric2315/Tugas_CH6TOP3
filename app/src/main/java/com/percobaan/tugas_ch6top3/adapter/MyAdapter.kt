package com.percobaan.tugas_ch6top3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.percobaan.tugas_ch6top3.model.Note
import com.percobaan.tugas_ch6top3.adapter.MyAdapter.ViewHolder
import com.percobaan.tugas_ch6top3.databinding.ListItemBinding

class MyAdapter (val data: List<Note>): RecyclerView.Adapter<ViewHolder>() {
    lateinit var onClick: (Note) -> Unit
    lateinit var onDeleteClick: (Int?) -> Unit
    lateinit var onEditClick :(Note) -> Unit
    fun setOnClickListener(onClick : (Note) -> Unit){
        this.onClick = onClick
    }
    fun setOnDeleteListerner(onClick : (Int?) -> Unit){
        this.onDeleteClick = onClick
    }
    fun setOnClickListerner(onClick : (Note) -> Unit){
        this.onEditClick = onClick
    }
    inner class ViewHolder (var binding: ListItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bindViewHolder(note: Note) {
            binding.btnEdit.setOnClickListener {
                onEditClick(note)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteClick(note.id)
            }
            binding.tvNamabarang.text = note.nama_barang
            binding.tvJumlah.text = note.jumlah
            binding.tvWarna.text = note.warna
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = ListItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(data[position])
    }

    override fun getItemCount(): Int = data.size
}