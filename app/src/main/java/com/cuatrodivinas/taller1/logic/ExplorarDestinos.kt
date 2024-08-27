package com.cuatrodivinas.taller1.logic

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.cuatrodivinas.taller1.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class ExplorarDestinos : AppCompatActivity() {
    private lateinit var detalles: ListView
    private lateinit var destinos: JSONArray
    private lateinit var destinosNombre: MutableList<String>
    private lateinit var categoria: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explorar_destinos)
        destinosNombre = mutableListOf()
        categoria = intent.getStringExtra("categoria") ?: "todos"
        inicializarElementos()
        eventoDetalles()
    }

    private fun inicializarElementos() {
        detalles = findViewById(R.id.destinosListView)
        val json = JSONObject(cargarJson() ?: return)
        destinos = json.getJSONArray("destinos")
        for (i in 0 until destinos.length()) {
            val jsonObject = destinos.getJSONObject(i)
            if (categoria.equals("todos", true) || categoria.equals(jsonObject.getString("categoria"), true)) {
                destinosNombre.add(jsonObject.getString("nombre"))
            }
        }

        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, destinosNombre)
        detalles.adapter = adaptador
    }

    private fun cargarJson(): String? {
        return try {
            val isStream: InputStream = assets.open("destinos.json")
            val size: Int = isStream.available()
            val buffer = ByteArray(size)
            isStream.read(buffer)
            isStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    private fun eventoDetalles() {
        detalles.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@ExplorarDestinos, DetailActivity::class.java)
            val destino = destinos.getJSONObject(position).toString()
            intent.putExtra("destino", destino)
            intent.putExtra("showButton", true)
            startActivity(intent)
        }
    }
}