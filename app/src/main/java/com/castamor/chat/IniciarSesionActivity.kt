package com.castamor.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.castamor.chat.databinding.ActivityIniciarSesionBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

private lateinit var binding: ActivityIniciarSesionBinding
private lateinit var auth: FirebaseAuth

class IniciarSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase
        auth = Firebase.auth

        // Verificar si el usuario ya inició sesión
        val currentUser = auth.currentUser
        if (currentUser != null) { IniciarActividad<MainActivity>() }

        binding.btnIniciarSesion.setOnClickListener {
            val correo = binding.inputCorreo.text.toString()
            val password = binding.inputPassword.text.toString()

            if (correo.isNotEmpty() && password.isNotEmpty()) {
                iniciarSesion(correo, password)
            } else {
                Notificacion("Rellena los campos correctamente")
            }
        }

        binding.btnRegistrarse.setOnClickListener { IniciarActividad<RegistrarseActivity>() }

    }

    private fun iniciarSesion(correo: String, password: String) {
        auth.signInWithEmailAndPassword(correo, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión correcto
                    IniciarActividad<MainActivity>()
                } else {
                    // Hubo error
                    Notificacion("Hubo un error al iniciar sesión. Verifica los datos e intenta nuevamente")
                }
            }
    }
}