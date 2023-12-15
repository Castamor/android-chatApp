package com.castamor.chat.recyclerviews.mensajes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.castamor.chat.R
import com.castamor.chat.clases.Mensaje
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

private lateinit var auth: FirebaseAuth

object TipoMensaje {
    const val MENSAJE_PROPIO = 0
    const val MENSAJE_RECIBIDO = 1
}

class MensajesAdaptador(val ListaMensajes: List<Mensaje>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TipoMensaje.MENSAJE_PROPIO -> {
                val view = layoutInflater.inflate(R.layout.mensaje_layout, parent, false)
                MensajeViewHolder(view)
            }
            TipoMensaje.MENSAJE_RECIBIDO -> {
                val view = layoutInflater.inflate(R.layout.mensaje_entrante_layout, parent, false)
                MensajeEntranteViewHolder(view)
            }
            else -> throw IllegalArgumentException("Vista desconocida")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mensaje = ListaMensajes[position]
        when (holder) {
            is MensajeViewHolder -> holder.mostrar(mensaje)
            is MensajeEntranteViewHolder -> holder.mostrar(mensaje)
        }
    }

    override fun getItemCount(): Int = ListaMensajes.size

    override fun getItemViewType(position: Int): Int {
        auth = Firebase.auth // Firebase
        val mensaje = ListaMensajes[position]

        return if (mensaje.idRemitente == auth.currentUser?.uid) {
            TipoMensaje.MENSAJE_PROPIO
        } else {
            TipoMensaje.MENSAJE_RECIBIDO
        }
    }
}
