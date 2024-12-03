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

            val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
            val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
            val loginButton = findViewById<Button>(R.id.loginButton)
            val registerButton = findViewById<Button>(R.id.registerButton)

            loginButton.setOnClickListener {
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }

            registerButton.setOnClickListener {

                Toast.makeText(this, "Tela de cadastro em desenvolvimento", Toast.LENGTH_SHORT).show()
            }
        }
    }

