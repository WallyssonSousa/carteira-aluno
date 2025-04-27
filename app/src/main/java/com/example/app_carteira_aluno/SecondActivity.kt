package com.example.app_carteira_aluno

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Toast


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val dbHelper = DatabaseHelper(this)

        val edtDisciplina: EditText = findViewById(R.id.edtDisciplina)
        val edtNota1: EditText = findViewById(R.id.edtNota1)
        val edtNota2: EditText = findViewById(R.id.edtNota2)
        val edtNota3: EditText = findViewById(R.id.edtNota3)
        val edtNota4: EditText = findViewById(R.id.edtNota4)
        val btnSalvar: Button = findViewById(R.id.btnSalvar)
        val btnVoltar: Button = findViewById(R.id.btnVoltar)
        val btnListarDisc: Button = findViewById(R.id.btnListarDisc)

        btnSalvar.setOnClickListener {
            val nomeDisc = edtDisciplina.text.toString().trim()

            if (nomeDisc.isEmpty()) {
                Toast.makeText(this, "O nome da disciplina não pode estar vazio!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dbHelper.verificarDisciplinaExiste(nomeDisc)) {
                Toast.makeText(this, "Essa disciplina já foi cadastrada!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val nota1 = edtNota1.text.toString().toDoubleOrNull() ?: 0.0
            val nota2 = edtNota2.text.toString().toDoubleOrNull() ?: 0.0
            val nota3 = edtNota3.text.toString().toDoubleOrNull() ?: 0.0
            val nota4 = edtNota4.text.toString().toDoubleOrNull() ?: 0.0

            dbHelper.inserirDisciplina(nomeDisc, nota1, nota2, nota3, nota4)

            val intent = Intent(this, DisciplinaListActivity::class.java)
            setResult(RESULT_OK)
            startActivity(intent)
        }

        btnVoltar.setOnClickListener {
            finish() // Fecha a tela atual e volta para a MainActivity
        }

        btnListarDisc.setOnClickListener{
            val intent = Intent(this, DisciplinaListActivity::class.java)
            startActivity(intent)
        }
    }
}
