package com.castamor.chat.recyclerviews.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.castamor.chat.MainActivity
import com.castamor.chat.R
import com.castamor.chat.clases.Chat
import com.castamor.chat.clases.Contacto

class ChatsAdaptador(val ListaChats: List<Chat>, val IniciarChat:(Contacto) -> Unit): RecyclerView.Adapter<ChatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatsViewHolder(layoutInflater.inflate(R.layout.chat_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val chat = ListaChats[position]
        holder.mostrar(chat, IniciarChat)
    }

    override fun getItemCount(): Int = ListaChats.size
}