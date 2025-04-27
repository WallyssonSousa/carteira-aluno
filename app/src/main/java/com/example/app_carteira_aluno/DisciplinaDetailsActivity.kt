package com.example.app_carteira_aluno

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisciplinaDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disciplina_details_activity)

        val dbHelper = DatabaseHelper(this)
        val disciplina = intent.getStringExtra("disciplina") ?: ""

        val txtDisciplina: TextView = findViewById(R.id.txtDisciplinaNome)
        val txtNotas: TextView = findViewById(R.id.txtNotas)
        val txtStatus: TextView = findViewById(R.id.txtStatus)
        val btnVoltar: Button = findViewById(R.id.btnVoltar)
        val btnExcluir: Button = findViewById(R.id.btnExcluir)

        txtDisciplina.text = "Disciplina: $disciplina"

        val notas = dbHelper.obterNotas(disciplina)
        txtNotas.text = "Notas: ${notas.joinToString(", ")}"

        val media = notas.sum() / notas.size
        txtStatus.text = if (media >= 6.0) "Aprovado" else "Reprovado"

        btnVoltar.setOnClickListener {
            finish()
        }

        btnExcluir.setOnClickListener{
            dbHelper.excluirDisciplina(nomeDisciplina = disciplina)
            setResult(RESULT_OK)
            finish()
        }
    }
}