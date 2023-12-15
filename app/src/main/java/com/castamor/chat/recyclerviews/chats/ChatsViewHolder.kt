package com.castamor.chat.recyclerviews.chats

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.castamor.chat.DataProvider
import com.castamor.chat.clases.Chat
import com.castamor.chat.clases.Contacto
import com.castamor.chat.convertirAHora
import com.castamor.chat.databinding.ChatLayoutBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ChatsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    // Firebase
    private val auth = Firebase.auth
    private val binding = ChatLayoutBinding.bind(view)

    fun mostrar(chat: Chat, iniciarChat: (Contacto) -> Unit) {
        val idContacto = listOf(chat.idPersonaUno, chat.idPersonaDos).filter { it != auth.currentUser?.uid }[0]

        val contacto = DataProvider.listaContactos.filter { it.id == idContacto }[0]

        binding.nombre.text = contacto.nombre
        binding.ultimoMsj.text = chat.ultimoMensaje
        binding.hora.text = chat.horaUltimoMensaje.convertirAHora()

        itemView.setOnClickListener {
            iniciarChat(Contacto(contacto.id, contacto.nombre))
        }

    }
}