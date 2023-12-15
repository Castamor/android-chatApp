package com.castamor.chat.recyclerviews.contactos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.castamor.chat.R
import com.castamor.chat.clases.Contacto

class ContactosAdaptador(val ListaContactos: List<Contacto>, val iniciarChat:(Contacto) -> Unit, val cambiarNombre: (Contacto) -> Unit): RecyclerView.Adapter<ContactoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ContactoViewHolder(layoutInflater.inflate(R.layout.contacto_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val contacto = ListaContactos[position]
        holder.mostrar(contacto, iniciarChat, cambiarNombre)
    }

    override fun getItemCount(): Int = ListaContactos.size
}