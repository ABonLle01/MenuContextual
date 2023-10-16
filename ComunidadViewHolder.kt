package com.example.ejercicio5kotlin

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ejercicio5kotlin.databinding.ComunidadBinding

class ComunidadViewHolder(view: View):ViewHolder(view) {

    val binding = ComunidadBinding.bind(view)

    fun render(item: Comunidad,onClickListener:(Comunidad)->Unit){
        binding.ivComunidad.setImageResource(item.imagen)
        binding.tvComunidadNombre.text=item.nombre
        itemView.setOnClickListener{
            onClickListener(item)
        }
    }

}