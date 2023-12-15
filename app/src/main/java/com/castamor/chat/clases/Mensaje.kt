package com.castamor.chat.clases

data class Mensaje(
    val idRemitente: String,
    val texto: String,
    val timestamp: Long,
) { constructor(): this("", "", 0) }
// El constructor es necesario porque Firebase necesita un constructor sin argumentos para poder instanciar objetos
