package com.example.odontovision.menu

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.odontovision.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TratamentoDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tratamento_data)

        // Configurar a seta de voltar
        val backArrow: ImageView = findViewById(R.id.back_arrow)
        backArrow.setOnClickListener {
            finish() // Finaliza a atividade e volta para a anterior
        }

        // Campos de data
        val startDate: EditText = findViewById(R.id.start_date)
        val endDate: EditText = findViewById(R.id.end_date)

        // Configurar DatePickerDialog para o campo de data inicial
        startDate.setOnClickListener {
            showDatePickerDialog(startDate)
        }

        // Configurar DatePickerDialog para o campo de data final
        endDate.setOnClickListener {
            showDatePickerDialog(endDate)
        }
    }

    // Função para exibir o DatePickerDialog
    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            editText.setText(dateFormat.format(selectedDate.time))
        }

        DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}
