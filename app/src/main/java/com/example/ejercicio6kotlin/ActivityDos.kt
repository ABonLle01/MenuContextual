package com.example.ejercicio6kotlin

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ActivityDos:AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos)

        val imagen = intent.getIntExtra("imagen",0)
        val nombre = intent.getStringExtra("nombre")
        val ivComunidad = findViewById<ImageView>(R.id.ivComunidad)
        val etNombre = findViewById<EditText>(R.id.etComunidadNombre)

        ivComunidad.setImageResource(imagen)
        etNombre.hint=nombre

        val btnGuardar=findViewById<Button>(R.id.bCambiar)
        btnGuardar.setOnClickListener{
            val intent=Intent(this,ActivityDos::class.java)
            val nuevoNombre = etNombre.text.toString()
            intent.putExtra("nombre",nuevoNombre)
            setResult(RESULT_OK, intent)
            finish()
        }

        val btnCancelar = findViewById<Button>(R.id.bCancelar)
        btnCancelar.setOnClickListener {
            finish()
        }


    }

    override fun onClick(p0: View?) {
//        val intent = Intent()
//        intent.putExtra("imagen",image)
//        intent.putExtra("nombre",name)
//        intentLaunch.launch(intent)
    }

}