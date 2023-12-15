package com.castamor.chat.recyclerviews.mensajes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.castamor.chat.clases.Mensaje
import com.castamor.chat.convertirAHora
import com.castamor.chat.databinding.MensajeEntranteLayoutBinding

class MensajeEntranteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = MensajeEntranteLayoutBinding.bind(view)

    fun mostrar(mensaje: Mensaje) {
        binding.textoMensaje.text = mensaje.texto
        binding.hora.text = mensaje.timestamp.convertirAHora()
    }
}