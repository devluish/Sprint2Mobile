package com.example.odontovision.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.odontovision.R

class EspecialidadeAdapter(
    private val especialidades: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<EspecialidadeAdapter.EspecialidadeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspecialidadeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_especialidade, parent, false)
        return EspecialidadeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EspecialidadeViewHolder, position: Int) {
        val especialidade = especialidades[position]
        holder.bind(especialidade)
    }

    override fun getItemCount(): Int = especialidades.size

    inner class EspecialidadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val especialidadeNome: TextView = itemView.findViewById(R.id.especialidade_nome)

        fun bind(especialidade: String) {
            especialidadeNome.text = especialidade
            itemView.setOnClickListener { onItemClick(especialidade) }
        }
    }
}
