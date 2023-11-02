package com.example.ejercicio6kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ActivityDos : AppCompatActivity(), View.OnClickListener {

    private lateinit var etNombre: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos)

        val imagen = intent.getIntExtra("imagen", 0)
        val nombre = intent.getStringExtra("nombre")
        val ivComunidad = findViewById<ImageView>(R.id.ivComunidad)
        etNombre = findViewById(R.id.etComunidadNombre)

        ivComunidad.setImageResource(imagen)
        etNombre.hint = nombre

        val btnGuardar = findViewById<Button>(R.id.bCambiar)
        btnGuardar.setOnClickListener {
            val nuevoNombre = etNombre.text.toString()
            val position = intent.getIntExtra("position", -1)

            val returnIntent = Intent()
            returnIntent.putExtra("nombre", nuevoNombre)
            returnIntent.putExtra("position", position)
            setResult(RESULT_OK, returnIntent)

            finish()
        }

        val btnCancelar = findViewById<Button>(R.id.bCancelar)
        btnCancelar.setOnClickListener {
            finish()
        }
    }

    override fun onClick(view: View?) {
        val nuevoNombre = etNombre.text.toString()
        val position = intent.getIntExtra("position", -1)

        val returnIntent = Intent()
        returnIntent.putExtra("nombre", nuevoNombre)
        returnIntent.putExtra("position", position)
        setResult(RESULT_OK, returnIntent)

        finish()
    }
}