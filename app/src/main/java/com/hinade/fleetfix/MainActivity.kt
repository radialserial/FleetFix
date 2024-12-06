package com.hinade.fleetfix

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val nomeEditText = findViewById<EditText>(R.id.nomeEditText)
            val senhaEditText = findViewById<EditText>(R.id.senhaEditText)
            val login = findViewById<Button>(R.id.login)
            val cadastrar = findViewById<Button>(R.id.cadastrar)

            login.setOnClickListener {
                val nome = nomeEditText.text.toString()
                val senha = senhaEditText.text.toString()

                if (nome.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }

            cadastrar.setOnClickListener {

                Toast.makeText(this, "Tela de cadastro em desenvolvimento", Toast.LENGTH_SHORT).show()
            }
        }
    }

