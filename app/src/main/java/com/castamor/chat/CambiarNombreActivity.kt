package com.castamor.chat

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.castamor.chat.databinding.ActivityCambiarNombreBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class CambiarNombreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCambiarNombreBinding
    private lateinit var DB: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambiarNombreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase
        DB = Firebase.database.reference

        val idContacto = intent.getStringExtra("id_contacto")
        val nombreContacto = intent.getStringExtra("nombre_contacto")

        binding.nombreActual.text = nombreContacto

        binding.btnActualizarNombre.setOnClickListener {
            val inputNuevoNombre = binding.inputNuevoNombre.text.toString()

            if (inputNuevoNombre.isEmpty()) {
                Notificacion("¡Escribe el nuevo nombre!")
            } else {
                DB.child("Contactos").child(idContacto!!).child("nombre").setValue(inputNuevoNombre)
                Notificacion("Nombre actualizado correctamente")
                finish()
            }
        }

        binding.btnRegresar.setOnClickListener { finish() }
    }
}