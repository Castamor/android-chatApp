package com.castamor.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.castamor.chat.clases.Contacto
import com.castamor.chat.databinding.ActivityRegistrarseBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

private lateinit var binding: ActivityRegistrarseBinding
private lateinit var auth: FirebaseAuth
private lateinit var DB: DatabaseReference

class RegistrarseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase
        auth = Firebase.auth
        DB = Firebase.database.reference

        binding.btnRegistrarse.setOnClickListener {
            val usuario = binding.inputNombre.text.toString()
            val correo = binding.inputCorreo.text.toString()
            val password = binding.inputPassword.text.toString()

            if (correo.isNotEmpty() && password.isNotEmpty() && usuario.isNotEmpty()) {
                registarUsuario(usuario, correo, password)
            } else {
                Notificacion("Rellena los campos")
            }
        }

        binding.btnIniciarSesion.setOnClickListener { IniciarActividad<IniciarSesionActivity>() }
    }

    private fun registarUsuario(usuario: String, correo: String, password: String) {
        auth.createUserWithEmailAndPassword(correo, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val id = auth.currentUser?.uid // En este punto ya hay un usario creado, entonces accedo a su id

                    val newUsuario = Contacto(id!!, usuario)
                    DB.child("Contactos").child(id).setValue(newUsuario)

                    IniciarActividad<MainActivity>()
                } else {
                    // Hubo error
                    Notificacion("Hubo un error al registrar cuenta. Verifica los datos e intenta de nuevo.\n(${task.exception?.message.toString()})")
                }
            }
    }
}