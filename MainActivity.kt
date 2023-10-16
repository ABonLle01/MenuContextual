package com.example.ejercicio5kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio5kotlin.ComunidadProvider.Companion.listasComunidad
import com.example.ejercicio5kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ComunidadAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private val copiaLista: MutableList<Comunidad> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvComunidades.layoutManager = LinearLayoutManager(this)

        binding.rvComunidades.adapter = ComunidadAdapter(ComunidadProvider.listasComunidad){
            comunidad -> onItemSelected(comunidad)
        }

        binding.rvComunidades.itemAnimator=DefaultItemAnimator()
        val decoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.rvComunidades.addItemDecoration(decoration)

        adapter = ComunidadAdapter(listasComunidad) { comunidad -> onItemSelected(comunidad) }
        binding.rvComunidades.adapter = adapter

        layoutManager = LinearLayoutManager(this)
        binding.rvComunidades.layoutManager = layoutManager

        copiaLista.addAll(listasComunidad)
    }

    private fun onItemSelected(comunidad: Comunidad) {
        Toast.makeText(
            this,
            "Has pulsado sobre ${comunidad.nombre}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Limpia -> {
                limpiar()
                true
            }
            R.id.Recarga -> {
                recargar()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun limpiar(){
        listasComunidad.clear()
        adapter.notifyDataSetChanged()
    }

    private fun recargar() {
        listasComunidad.addAll(copiaLista)
        adapter.notifyDataSetChanged()
    }



}