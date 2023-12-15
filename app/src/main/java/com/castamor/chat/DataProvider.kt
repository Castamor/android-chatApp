package com.castamor.chat

import android.util.Log
import com.castamor.chat.clases.Chat
import com.castamor.chat.clases.Contacto
import com.castamor.chat.clases.Mensaje
import com.castamor.chat.recyclerviews.chats.ChatsAdaptador
import com.castamor.chat.recyclerviews.contactos.ContactosAdaptador
import com.castamor.chat.recyclerviews.mensajes.MensajesAdaptador
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class DataProvider {

    companion object {
        val listaContactos = mutableListOf<Contacto>()
        val listaMensajes = mutableListOf<Mensaje>()
        val listaChats = mutableListOf<Chat>()

        fun agregarContacto(contacto: Contacto) = listaContactos.add(contacto)
        fun agregarMensaje(mensaje: Mensaje) = listaMensajes.add(mensaje)
        fun agregarChat(chat: Chat) = listaChats.add(chat)

        // Firebase
        val DB = Firebase.database.reference
        val auth = Firebase.auth

        fun obtenerContactos(adaptadorContactos: ContactosAdaptador) {
            // Obtener contactos para mostrar
            DB.child("Contactos").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val contactos = snapshot.children.mapNotNull { it.getValue(Contacto::class.java) }

                    listaContactos.clear()
                    contactos.forEach { agregarContacto(it) }
                    listaContactos.sortBy { it.nombre }
                    adaptadorContactos.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) { Log.e("Error", error.message.toString()) }
            })
        }

        fun obtenerMensajes(adaptadorMensajes: MensajesAdaptador, chatKey: String) {
            // Obtener mensajes para mostrar
            DB.child("Mensajes").child(chatKey).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val mensajes = snapshot.children.mapNotNull { it.getValue(Mensaje::class.java) }

                    listaMensajes.clear()
                    mensajes.forEach { agregarMensaje(it) }

                    adaptadorMensajes.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) { Log.e("Error", error.message.toString()) }
            })
        }

        fun obtenerChats(adaptadorChats: ChatsAdaptador) {
            // Obtener chats a mostrar
            DB.child("Chats").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val chats = snapshot.children.mapNotNull { it.getValue(Chat::class.java) }
                    val idUsuarioActual = auth.currentUser?.uid

                    listaChats.clear()
                    chats.filter { it.idPersonaUno == idUsuarioActual || it.idPersonaDos == idUsuarioActual }.forEach { agregarChat(it) }
                    listaChats.sortByDescending { it.horaUltimoMensaje }

                    adaptadorChats.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) { Log.e("Error", error.message.toString()) }
            })
        }
    }

}