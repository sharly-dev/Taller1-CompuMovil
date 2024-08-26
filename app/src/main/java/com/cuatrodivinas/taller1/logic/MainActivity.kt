package com.cuatrodivinas.taller1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cuatrodivinas.taller1.logic.ExplorarDestinos

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Coger a los peladitos del xml
        val spinnerCategorias: Spinner = findViewById(R.id.categorias_viaje)
        val botonExplorarDestinos: Button = findViewById(R.id.explorar_destinos)
        val botonFavoritos: Button = findViewById(R.id.favoritos)
        val botonRecomendaciones: Button = findViewById(R.id.recomendaciones)

        val intent = Intent(this, ExplorarDestinos::class.java)
        intent.putExtra("categoria", spinnerCategorias.selectedItem.toString())

        botonExplorarDestinos.setOnClickListener {
            startActivity(intent)
        }
    }
}