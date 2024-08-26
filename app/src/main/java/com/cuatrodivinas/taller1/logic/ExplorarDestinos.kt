package com.cuatrodivinas.taller1.logic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cuatrodivinas.taller1.MainActivity
import com.cuatrodivinas.taller1.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.io.Serializable

class ExplorarDestinos : AppCompatActivity() {
    private lateinit var detalles:ListView
    private lateinit var destinos:JSONArray
    private lateinit var destinosNombre:MutableList<String>
    private lateinit var categoria:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explorar_destinos)
        destinosNombre = mutableListOf()
        categoria = intent.getStringExtra("categoria")!!
        inicializarElementos()
        eventoDetalles()
    }

    fun inicializarElementos(){
        detalles = findViewById(R.id.destinosListView)
        //ListView
        val json = JSONObject(cargarJson())
        destinos = json.getJSONArray("destinos")
        for (i in 0 until destinos.length()){
            val jsonObject = destinos.getJSONObject(i)
            if(categoria.equals("todos", true) ||
                categoria.equals(jsonObject.getString("categoria"), true)){
                destinosNombre.add(jsonObject.getString("nombre"))
            }
        }

        val adaptador = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            destinosNombre)
        detalles.adapter = adaptador
    }

    fun cargarJson(): String?{
        var json: String? = null
        try {
            val isStream: InputStream = assets.open("destinos.json")
            val size:Int = isStream.available()
            val buffer = ByteArray(size)
            isStream.read(buffer)
            isStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun eventoDetalles(){
        detalles.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val intent = Intent(this@ExplorarDestinos, MainActivity::class.java)
                val bundle = Bundle()
                bundle.putString("destinos", destinos.get(position).toString())
                intent.putExtra("bundle", bundle)
                startActivity(intent)
            }
        })
    }
}