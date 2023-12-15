package com.castamor.chat.clases

data class Chat (
    val idPersonaUno: String,
    val idPersonaDos: String,
    val ultimoMensaje: String,
    val horaUltimoMensaje: Long,
) { constructor() : this("", "", "", 0) }
// El constructor es necesario porque Firebase necesita un constructor sin argumentos para poder instanciar objetos
