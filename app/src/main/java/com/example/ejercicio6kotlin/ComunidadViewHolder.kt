package com.example.ejercicio6kotlin

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ejercicio6kotlin.databinding.ComunidadBinding

class ComunidadViewHolder(view: View):ViewHolder(view), View.OnCreateContextMenuListener {

    val binding = ComunidadBinding.bind(view)
    private lateinit var comunidad: Comunidad

    fun render(item: Comunidad,onClickListener:(Comunidad)->Unit){
        comunidad=item
        binding.ivComunidad.setImageResource(item.imagen)
        binding.tvComunidadNombre.text=item.nombre
        Glide.with(binding.ivComunidad.context).load(item.imagen).into(binding.ivComunidad)
        itemView.setOnClickListener{
            onClickListener(item)
        }
        itemView.setOnCreateContextMenuListener(this)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.setHeaderTitle(comunidad.nombre)
        menu.add(this.adapterPosition,0,0,"Eliminar")
    }

}