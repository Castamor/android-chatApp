package com.castamor.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.castamor.chat.clases.Contacto
import com.castamor.chat.databinding.ActivityMainBinding
import com.castamor.chat.recyclerviews.chats.ChatsAdaptador
import com.castamor.chat.recyclerviews.contactos.ContactosAdaptador
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase
        auth = Firebase.auth

        iniciarRecyclerViewContactos()
        iniciarRecyclerViewChats()

        binding.btnCerrarSesion.setOnClickListener {
            auth.signOut()
            Notificacion("Cerraste sesíon")
            IniciarActividad<IniciarSesionActivity>()
        }
    }

    fun iniciarRecyclerViewContactos() {
        val adaptadorContactos = ContactosAdaptador(
            DataProvider.listaContactos,
            { contacto -> abrirChat(contacto)},
            { contacto -> cambiarNombre(contacto) }
        )

        binding.rvContactos.layoutManager = LinearLayoutManager(this)
        binding.rvContactos.adapter = adaptadorContactos

        DataProvider.obtenerContactos(adaptadorContactos)
    }

    fun iniciarRecyclerViewChats() {
        val adaptadorChats = ChatsAdaptador(DataProvider.listaChats) { abrirChat(it) }
        binding.rvChats.layoutManager = LinearLayoutManager(this)
        binding.rvChats.adapter = adaptadorChats

        DataProvider.obtenerChats(adaptadorChats)
    }

    fun abrirChat(contacto: Contacto) {
        // Lógica para abrir un chat
        val extras = Bundle()
        extras.putString("id_contacto", contacto.id)
        extras.putString("nombre_contacto", contacto.nombre)
        extras.putString("id_propio", auth.currentUser?.uid)

        IniciarActividad<ChatActivity>(extras, finalizarActActual = false)
    }

    fun cambiarNombre(contacto: Contacto) {
        // Lógica para actualizar nombre
        val extras = Bundle()
        extras.putString("id_contacto", contacto.id)
        extras.putString("nombre_contacto", contacto.nombre)

        IniciarActividad<CambiarNombreActivity>(extras, finalizarActActual = false)
    }
}