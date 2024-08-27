package com.cuatrodivinas.taller1.logic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cuatrodivinas.taller1.R
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val destinoJson = intent.getStringExtra("destino")
        if (destinoJson != null) {
            val destino = JSONObject(destinoJson)

            val cityTextView: TextView = findViewById(R.id.city)
            val countryTextView: TextView = findViewById(R.id.country)
            val categoryTextView: TextView = findViewById(R.id.category)
            val planTextView: TextView = findViewById(R.id.plan)
            val priceTextView: TextView = findViewById(R.id.price)
            val buttonFavorites: Button = findViewById(R.id.button_favorites)

            cityTextView.text = destino.getString("nombre")
            countryTextView.text = destino.getString("pais")
            categoryTextView.text = destino.getString("categoria")
            planTextView.text = destino.getString("plan")
            priceTextView.text = destino.getInt("precio").toString()

            val showButton = intent.getBooleanExtra("showButton", false)
            buttonFavorites.visibility = if (showButton) View.VISIBLE else View.GONE
        } else {
            finish() // Close the activity or show an error message
        }

        val arrowBack: ImageView = findViewById(R.id.arrow_back)
        arrowBack.setOnClickListener {
            finish() // Finish the current activity and return to the previous one
        }
    }
}