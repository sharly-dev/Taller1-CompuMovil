package com.cuatrodivinas.taller1.logic

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cuatrodivinas.taller1.R
import com.cuatrodivinas.taller1.data.Data.Companion.favoritos
import com.cuatrodivinas.taller1.data.Destino

class DetailActivity : AppCompatActivity() {
    private lateinit var destino:Bundle
    private var textoNombre: TextView = findViewById(R.id.nombre_destino)
    private var textoPais: TextView = findViewById(R.id.pais_destino)
    private var textoCategoria: TextView = findViewById(R.id.categoria_destino)
    private var textoPlan: TextView = findViewById(R.id.plan_destino)
    private var textoPrecio: TextView = findViewById(R.id.precio_destino)
    private var botonAddFavoritos: Button = findViewById(R.id.boton_add_favoritos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Si viene de Explorar Destinos (es decir, estamos en Detalles)
        if(intent.getBundleExtra("bundle") == null) {
            setDestino()
            botonAddFavoritos.visibility = View.GONE
        } else {    // Si viene del Menu, del MainActivity (es decir, estamos en Recomendaciones)
            destino = intent.getBundleExtra("bundle")!!
            for (i in 0 until favoritos.size){
                if(favoritos[i].getId() == destino.getInt("id")) {
                    botonAddFavoritos.visibility = View.GONE
                }
            }
        }

        inicializarElementos()
    }

    private fun setDestino(){
        // Iterar la lista de favoritos para ver la categoria mas repetida
        val categorias = mutableMapOf<String, Int>()
        for (i in 0 until favoritos.size){
            val categoria = favoritos[i].getCategoria()
            if(categorias.containsKey(categoria)){
                categorias[categoria] = categorias[categoria]!! + 1
            }else{
                categorias[categoria] = 1
            }
        }

        // Obtener la categoria mas repetida
        var max = 0
        var categoriaMasRepetida = ""
        for (entry in categorias){
            if(entry.value > max){
                max = entry.value
                categoriaMasRepetida = entry.key
            }
        }

        val posiblesDestinos = mutableListOf<Bundle>()
        for (i in 0 until favoritos.size){
            if(favoritos[i].getCategoria() == categoriaMasRepetida){
                val bundle = Bundle()
                bundle.putInt("id", favoritos[i].getId())
                bundle.putString("nombre", favoritos[i].getNombre())
                bundle.putString("pais", favoritos[i].getPais())
                bundle.putString("categoria", favoritos[i].getCategoria())
                bundle.putString("plan", favoritos[i].getPlan())
                bundle.putInt("precio", favoritos[i].getPrecio())
                posiblesDestinos.add(bundle)
            }
        }

        // Numero aleatorio entre 0 y posiblesDestinos.size
        val random = (0 until posiblesDestinos.size).random()

        this.destino = posiblesDestinos[random]
    }

    private fun inicializarElementos(){
        val nombre:String = destino.getString("nombre").toString()
        val pais:String = destino.getString("pais").toString()
        val categoria:String = destino.getString("categoria").toString()
        val plan:String = destino.getString("plan").toString()
        val precio:Int = destino.getInt("precio")

        textoNombre.text = nombre
        textoPais.text = pais
        textoCategoria.text = categoria
        textoPlan.text = plan
        val precioStr = "USD $precio"
        textoPrecio.text = precioStr

        if(botonAddFavoritos.visibility != View.GONE){
            botonAddFavoritos.setOnClickListener {
                val destiny = Destino(destino.getInt("id"), nombre, pais, categoria, plan, precio)
                favoritos.add(destiny)
                Toast.makeText(this, "$nombre añadido a favoritos", Toast.LENGTH_SHORT).show()
                botonAddFavoritos.visibility = View.GONE
            }
        }
    }
}