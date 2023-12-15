package com.castamor.chat.recyclerviews.contactos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.castamor.chat.clases.Contacto
import com.castamor.chat.databinding.ContactoLayoutBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ContactoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    // Firebase
    val auth = Firebase.auth

    val binding = ContactoLayoutBinding.bind(view)

    fun mostrar(contacto: Contacto, iniciarChat: (Contacto) -> Unit, cambiarNombre: (Contacto) -> Unit) {

        if (contacto.id != auth.currentUser?.uid) {
            binding.nombreContacto.text = contacto.nombre
            itemView.setOnClickListener { iniciarChat(contacto) }
        } else {
            binding.nombreContacto.text = "${contacto.nombre} (Click para actualizar tu nombre)"
            itemView.setOnClickListener { cambiarNombre(contacto) }
        }
    }
}