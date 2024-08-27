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
    private lateinit var spinnerCategorias: Spinner
    private lateinit var botonExplorarDestinos: Button
    private lateinit var botonFavoritos: Button
    private lateinit var botonRecomendaciones: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views after setting the content view
        spinnerCategorias = findViewById(R.id.categorias_viaje)
        botonExplorarDestinos = findViewById(R.id.explorar_destinos)
        botonFavoritos = findViewById(R.id.favoritos)
        botonRecomendaciones = findViewById(R.id.recomendaciones)

        inicializarBotones()
    }

    private fun inicializarBotones() {
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
            if (favoritos.isEmpty()) {
                Toast.makeText(this, "Primero agrega un destino a favoritos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, DetailActivity::class.java)
                startActivity(intent)
            }
        }
    }
}