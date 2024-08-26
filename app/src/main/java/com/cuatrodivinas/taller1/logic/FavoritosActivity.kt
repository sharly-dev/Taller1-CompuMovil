package com.cuatrodivinas.taller1.logic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.cuatrodivinas.taller1.R
import com.cuatrodivinas.taller1.data.Data.Companion.favoritos

class FavoritosActivity : AppCompatActivity() {
    private lateinit var destinosNombre:MutableList<String>
    private lateinit var detalles:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        destinosNombre = mutableListOf()

        inicializarElementos()
        eventoDetalles()
    }

    private fun inicializarElementos(){
        detalles = findViewById(R.id.destinosListView)

        for (i in 0 until favoritos.size){
            destinosNombre.add(favoritos[i].getNombre())
        }

        val adaptador = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            destinosNombre)
        detalles.adapter = adaptador
    }

    private fun eventoDetalles(){
        detalles.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val intent = Intent(this@FavoritosActivity, DetailActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("id", favoritos[position].getId())
                bundle.putString("nombre", favoritos[position].getNombre())
                bundle.putString("pais", favoritos[position].getPais())
                bundle.putString("categoria", favoritos[position].getCategoria())
                bundle.putString("plan", favoritos[position].getPlan())
                bundle.putInt("precio", favoritos[position].getPrecio())
                intent.putExtra("bundle", bundle)
                startActivity(intent)
            }
        }
    }
}