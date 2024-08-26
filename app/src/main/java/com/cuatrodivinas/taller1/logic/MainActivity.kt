package com.cuatrodivinas.taller1.logic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cuatrodivinas.taller1.R
import com.cuatrodivinas.taller1.data.Data.Companion.favoritos

class MainActivity : AppCompatActivity() {
    // Coger a los peladitos del xml
    private val spinnerCategorias: Spinner = findViewById(R.id.categorias_viaje)
    private val botonExplorarDestinos: Button = findViewById(R.id.explorar_destinos)
    private val botonFavoritos: Button = findViewById(R.id.favoritos)
    private val botonRecomendaciones: Button = findViewById(R.id.recomendaciones)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarBotones()
    }

    private fun inicializarBotones(){
        botonExplorarDestinos.setOnClickListener {
            val intent = Intent(this, ExplorarDestinosActivity::class.java)
            intent.putExtra("categoria", spinnerCategorias.selectedItem.toString())
            startActivity(intent)
        }

        botonFavoritos.setOnClickListener {
            val intent = Intent(this, FavoritosActivity::class.java)
            startActivity(intent)
        }

        botonRecomendaciones.setOnClickListener {
            if (favoritos.size == 0) {
                Toast.makeText(this, "Primero agrega un destino a favoritos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
            }
        }
    }
}