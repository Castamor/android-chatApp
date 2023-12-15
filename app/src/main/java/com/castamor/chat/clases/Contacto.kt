package com.castamor.chat.clases

data class Contacto (
    val id: String = "",
    val nombre: String = ""
) { constructor() : this("", "") }
// El constructor es necesario porque Firebase necesita un constructor sin argumentos para poder instanciar objetos