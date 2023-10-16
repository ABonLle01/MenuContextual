package com.example.ejercicio5kotlin

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ComunidadAdapter(private val comLista:List<Comunidad>,private val onClickListener:(Comunidad)->Unit):RecyclerView.Adapter<ComunidadViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComunidadViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComunidadViewHolder(layoutInflater.inflate(R.layout.comunidad,parent,false))
    }

    override fun onBindViewHolder(holder: ComunidadViewHolder, position: Int) {
        val item = comLista[position]
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int {
        return comLista.size
    }
}