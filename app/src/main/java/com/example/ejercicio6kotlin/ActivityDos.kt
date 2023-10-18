package com.example.ejercicio6kotlin

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ActivityDos:AppCompatActivity(), View.OnClickListener {

    private lateinit var intentLaunch:ActivityResultLauncher<Intent>
    val nuevoNombre=findViewById<EditText>(R.id.etComunidadNombre)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos)

        val btnGuardar=findViewById<Button>(R.id.bCambiar)
        btnGuardar.setOnClickListener(this)

        intentLaunch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if ((data != null) && data.hasExtra("nombre")) {
                    val nombreModificado = data.getStringExtra("nombre")
                    nuevoNombre.setText(nombreModificado)
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        val intent=Intent()
        val nombre=nuevoNombre.text.toString()
        intent.putExtra("nombre",nombre)
        setResult(RESULT_OK, intent)
        intentLaunch.launch(intent)
    }

}


