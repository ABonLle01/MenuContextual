package com.example.ejercicio6kotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicio6kotlin.ComunidadProvider.Companion.listasComunidad
import com.example.ejercicio6kotlin.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ComunidadAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var intentLaunch:ActivityResultLauncher<Intent>


    private val copiaLista: MutableList<Comunidad> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvComunidades.layoutManager = LinearLayoutManager(this)

        binding.rvComunidades.adapter = ComunidadAdapter(listasComunidad){
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

        intentLaunch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val nombre = result.data?.extras?.getString("nombre")
                if (!nombre.isNullOrBlank()) {
                    val comunidadAfectada = listasComunidad[result.data?.extras?.getInt("position") ?: -1]
                    comunidadAfectada.nombre = nombre
                    adapter.notifyDataSetChanged()
                }
            }
        }

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

    override fun onContextItemSelected(item: MenuItem): Boolean {

        lateinit var comunidadAfectada:Comunidad
        lateinit var miIntent:Intent

        when(item.itemId){
            0-> {
                val alert=
                    AlertDialog.Builder(this).setTitle("Eliminar ${comunidadAfectada.nombre}")
                        .setMessage(
                            "¿Estás seguro de que quieres eliminar ${comunidadAfectada.nombre}?"
                        )
                        .setNeutralButton("Cerrar",null).setPositiveButton(
                            "Aceptar"
                        ){_,_ ->
                            display("Se ha eliminado ${comunidadAfectada.nombre}")
                            listasComunidad.removeAt(item.groupId)
                            adapter.notifyItemRemoved(item.groupId)
                            adapter.notifyItemRangeChanged(item.groupId, listasComunidad.size)
                            binding.rvComunidades.adapter = ComunidadAdapter(listasComunidad){
                                comunidad->onItemSelected(comunidad)
                            }
                        }.create()
                alert.show()
            }

            1 -> {
                val alertDialog = AlertDialog.Builder(this).setTitle("Editar $comunidadAfectada")
                val input = EditText(this)
                input.setText(comunidadAfectada.nombre)

                alertDialog.setView(input)

                alertDialog.setPositiveButton("Guardar") { _, _ ->
                    val nuevoNombre = input.text.toString()
                    comunidadAfectada.nombre = nuevoNombre
                    adapter.notifyDataSetChanged()

                }

                alertDialog.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.cancel()
                }

                alertDialog.show()
            }



            else->return super.onContextItemSelected(item)
        }
        return true
    }

    private fun display(message: String) {
        Snackbar.make(binding.root,message,Snackbar.LENGTH_SHORT).show()
    }

}