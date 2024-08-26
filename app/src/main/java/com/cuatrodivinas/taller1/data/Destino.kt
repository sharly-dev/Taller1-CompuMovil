package com.cuatrodivinas.taller1.data

class Destino(
    private var id: Int,
    private var nombre: String,
    private var pais: String,
    private var categoria: String,
    private var plan: String,
    private var precio: Int
) {

    fun getId():Int{
        return id
    }

    fun getNombre():String{
        return nombre
    }

    fun getPais():String{
        return pais
    }

    fun getCategoria():String{
        return categoria
    }

    fun getPlan():String{
        return plan
    }

    fun getPrecio():Int{
        return precio
    }
}
