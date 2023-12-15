package com.castamor.chat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Activity.Notificacion(texto: String) {
    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : Activity> Activity.IniciarActividad(extra: Bundle? = null, finalizarActActual: Boolean = true) {

    val intent = Intent(this, T::class.java)
    extra?.let { intent.putExtras(it) }

    startActivity(intent)

    if (finalizarActActual){ finish() }
}

fun Long.convertirAHora(): String {
    val fecha = Date(this)
    val formateador = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formateador.format(fecha)
}