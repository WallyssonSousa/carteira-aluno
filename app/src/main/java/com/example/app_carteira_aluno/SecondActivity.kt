package com.example.app_carteira_aluno

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val edtNota1: EditText = findViewById(R.id.edtNota1)
        val edtNota2: EditText = findViewById(R.id.edtNota2)
        val edtNota3: EditText = findViewById(R.id.edtNota3)
        val edtNota4: EditText = findViewById(R.id.edtNota4)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)
        val txtResultado: TextView = findViewById(R.id.txtResultado)
        val btnVoltar: Button = findViewById(R.id.btnVoltar)
        val txtAprovadoReprovado: TextView = findViewById(R.id.txtAprovadoReprovado)

        btnCalcular.setOnClickListener {
            val nota1 = edtNota1.text.toString().toDoubleOrNull() ?: 0.0
            val nota2 = edtNota2.text.toString().toDoubleOrNull() ?: 0.0
            val nota3 = edtNota3.text.toString().toDoubleOrNull() ?: 0.0
            val nota4 = edtNota4.text.toString().toDoubleOrNull() ?: 0.0

            val mediaFinal = (nota1 + nota2 + nota3 + nota4) / 4

            txtResultado.text = "Média Final: %.2f".format(mediaFinal)

            if(mediaFinal >= 6){
                txtAprovadoReprovado.text = "Aprovado"
            } else {
                txtAprovadoReprovado.text = "Reprovado"
            }
        }

        btnVoltar.setOnClickListener {
            finish() // Fecha a tela atual e volta para a MainActivity
        }
    }
}
