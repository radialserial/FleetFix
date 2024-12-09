package com.hinade.fleetfix

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val nomeEditText = findViewById<EditText>(R.id.nomeEditText)
        val senhaEditText = findViewById<EditText>(R.id.senhaEditText)
        val repetirSenhaEditText = findViewById<EditText>(R.id.repetirSenhaEditText)
        val usarTypeSpinner = findViewById<Spinner>(R.id.usarTypeSpinner)
        val cadastrar = findViewById<Button>(R.id.cadastrar)


        val usarTypes = resources.getStringArray(R.array.usar_types)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, usarTypes)
        usarTypeSpinner.adapter = adapter

        cadastrar.setOnClickListener {
            val nome = nomeEditText.text.toString()
            val senha = senhaEditText.text.toString()
            val repetirSenha = repetirSenhaEditText.text.toString()
            val usarType = usarTypeSpinner.selectedItem.toString()

            if (nome.isEmpty() || senha.isEmpty() || repetirSenha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else if (senha != repetirSenha) {
                Toast.makeText(this, "As senhas não coincidem!", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)


            }
        }
    }
}