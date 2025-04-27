package com.example.app_carteira_aluno

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "EscolaDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE Disciplina (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                nota1 REAL,
                nota2 REAL,
                nota3 REAL,
                nota4 REAL
            );
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Disciplina")
        onCreate(db)
    }

    fun inserirDisciplina(nome: String, nota1: Double, nota2: Double, nota3: Double, nota4: Double) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nome", nome)
            put("nota1", nota1)
            put("nota2", nota2)
            put("nota3", nota3)
            put("nota4", nota4)
        }
        db.insert("Disciplina", null, values)
        db.close()
    }

    fun listarDisciplinas(): List<String> {
        val db = readableDatabase
        val lista = mutableListOf<String>()
        val cursor = db.rawQuery("SELECT nome FROM Disciplina", null)

        while (cursor.moveToNext()) {
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            lista.add(nome)
        }
        cursor.close()
        db.close()
        return lista
    }

    fun obterNotas(disciplina: String): List<Double> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT nota1, nota2, nota3, nota4 FROM Disciplina WHERE nome = ?", arrayOf(disciplina))
        val notas = mutableListOf<Double>()

        if (cursor.moveToFirst()) {
            notas.add(cursor.getDouble(cursor.getColumnIndexOrThrow("nota1")))
            notas.add(cursor.getDouble(cursor.getColumnIndexOrThrow("nota2")))
            notas.add(cursor.getDouble(cursor.getColumnIndexOrThrow("nota3")))
            notas.add(cursor.getDouble(cursor.getColumnIndexOrThrow("nota4")))
        }
        cursor.close()
        db.close()
        return notas
    }
    fun excluirDisciplina(nomeDisciplina: String) {
        val db = writableDatabase
        db.delete("Disciplina", "nome = ?", arrayOf(nomeDisciplina))
        db.close()
    }
    fun verificarDisciplinaExiste(nomeDisciplina: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT nome FROM Disciplina WHERE nome = ?", arrayOf(nomeDisciplina))
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }
}