package com.example.odontovision.menu

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.odontovision.R
import com.example.odontovision.adapter.EspecialidadeAdapter

class BuscarDentistaActivity : AppCompatActivity() {
    private var listaVisivel = false // Controla a visibilidade da lista

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_dentista)

        val backArrow: ImageView = findViewById(R.id.back_arrow)
        backArrow.setOnClickListener {
            finish()
        }

        val especialidadeText: TextView = findViewById(R.id.especialidade_text)
        val especialidadesRecycler: RecyclerView = findViewById(R.id.especialidades_recycler)

        // Lista de especialidades com descrições
        val especialidades = listOf(
            "Todas as especialidades",
            "Emergência",
            "Clínico Geral",
            "Endodontia",
            "Prótese Dentária"
        )

        // Configurar o RecyclerView e Adapter
        especialidadesRecycler.layoutManager = LinearLayoutManager(this)
        especialidadesRecycler.adapter = EspecialidadeAdapter(especialidades) { especialidade ->
            selecionarEspecialidade(especialidade)
        }

        // Alternar visibilidade do RecyclerView ao clicar no campo de especialidade
        especialidadeText.setOnClickListener {
            listaVisivel = !listaVisivel
            especialidadesRecycler.visibility = if (listaVisivel) View.VISIBLE else View.GONE
        }
    }

    private fun selecionarEspecialidade(especialidade: String) {
        val especialidadeText: TextView = findViewById(R.id.especialidade_text)
        especialidadeText.text = especialidade

        // Esconde a lista após a seleção
        val especialidadesRecycler: RecyclerView = findViewById(R.id.especialidades_recycler)
        especialidadesRecycler.visibility = View.GONE
        listaVisivel = false
    }
}
