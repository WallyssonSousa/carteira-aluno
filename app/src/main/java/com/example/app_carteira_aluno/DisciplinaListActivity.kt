package com.example.app_carteira_aluno

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class DisciplinaListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.disciplina_list)

        val dbHelper = DatabaseHelper(this)
        val disciplinas = dbHelper.listarDisciplinas()
        val layoutDisciplinas: LinearLayout = findViewById(R.id.LLyt_disciplinas)
        val btnVoltar: Button = findViewById(R.id.btnVoltar)


        disciplinas.forEach { disciplina ->
            val button = Button(this)
            button.text = disciplina
            button.setOnClickListener {
                val intent = Intent(this, DisciplinaDetailsActivity::class.java)
                intent.putExtra("disciplina", disciplina)
                startActivity(intent)
            }
            layoutDisciplinas.addView(button)
        }
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}