package com.example.odontovision.menu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.odontovision.R

class TratamentoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tratamento)

        // Configurar a seta de voltar para finalizar a atividade e retornar à anterior
        val backArrow: ImageView = findViewById(R.id.back_arrow)
        backArrow.setOnClickListener {
            finish()
        }

        // Configurar o botão Continuar para abrir a atividade TratamentoDataActivity
        val continueButton: Button = findViewById(R.id.continue_button)
        continueButton.setOnClickListener {
            val intent = Intent(this, TratamentoDataActivity::class.java)
            startActivity(intent)
        }
    }
}
