package com.castamor.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.castamor.chat.clases.Chat
import com.castamor.chat.clases.Mensaje
import com.castamor.chat.databinding.ActivityChatBinding
import com.castamor.chat.recyclerviews.mensajes.MensajesAdaptador
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var DB: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase
        DB = Firebase.database.reference

        val idContacto = intent.getStringExtra("id_contacto")
        val nombreContacto = intent.getStringExtra("nombre_contacto")
        val idUsuarioActual = intent.getStringExtra("id_propio")

        val chatKey = generarChatKey(idContacto!!, idUsuarioActual!!)
        val idsOrdenados = ordenarIds(chatKey)

        val idSender = idsOrdenados.filter { it == idUsuarioActual }[0]
        val idRemitente = idsOrdenados.filter { it != idUsuarioActual }[0]

        binding.nombrePersona.text = nombreContacto // Poner en nombre del contacto en la parte de arriba

        iniciarRecyclerViewMensajes(chatKey)

        binding.btnEnviarMensaje.setOnClickListener {

            val textoMensaje = binding.inputMensaje.text.toString()

            if (textoMensaje.isEmpty()) {
                Notificacion("¡Escribe algo!")
            } else {
                val timestamp: Long = System.currentTimeMillis()

                val mensajeObjeto = Mensaje(idSender, textoMensaje, timestamp) // Instanciar clase Mensaje
                val chatObjeto = Chat(idSender, idRemitente, textoMensaje, timestamp) // Instanciar clase Chat

                // Agregar/Actualizar la base de datos
                DB.child("Mensajes").child(chatKey).push().setValue(mensajeObjeto)
                DB.child("Chats").child(chatKey).setValue(chatObjeto)

                iniciarRecyclerViewMensajes(chatKey)
                binding.inputMensaje.text.clear()
            }
        }

        binding.btnRegresar.setOnClickListener { finish() } // Regresar a la pantalla principal
    }

    private fun generarChatKey(idUno: String, idDos: String): String {
        val ids_ordenados = listOf(idUno, idDos).sorted()
        return "${ids_ordenados[0]}_${ids_ordenados[1]}"
    }

    private fun ordenarIds(chatKey: String): List<String> {
        return chatKey.split("_")
    }

    private fun iniciarRecyclerViewMensajes(chatKey: String) {
        val adaptadorMensajes = MensajesAdaptador(DataProvider.listaMensajes)
        val layoutManager =  LinearLayoutManager(this)

        binding.rvMensajes.layoutManager = layoutManager
        binding.rvMensajes.adapter = adaptadorMensajes

        DataProvider.obtenerMensajes(adaptadorMensajes, chatKey)

        adaptadorMensajes.notifyDataSetChanged()
        layoutManager.scrollToPosition(adaptadorMensajes.itemCount - 1)
    }

}