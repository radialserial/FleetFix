package com.hinade.fleetfix.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hinade.fleetfix.R
import com.hinade.fleetfix.databinding.FragmentCadastroBinding
import com.hinade.fleetfix.model.usuario.TipoUsuario
import com.hinade.fleetfix.model.usuario.Usuario
import com.hinade.fleetfix.viewmodel.CadastroViewModel

/**
 * Um fragmento representando a tela de cadastro.
 */
class CadastroFragment : Fragment() {

    private lateinit var binding: FragmentCadastroBinding
    private val cadastroViewModel by viewModels<CadastroViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCadastroBinding.inflate(layoutInflater)

        with(binding) {

            val tiposUsuario =
                listOf("Tipo de usuário").plus(TipoUsuario.entries.map { it.nome })
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                tiposUsuario
            )
            tipoUsuarioSpinner.adapter = adapter

            cadastroButton.setOnClickListener {

                val nome = nomeEditText.text.toString()
                val telefone = telefoneEditText.text.toString()
                val login = loginEditText.text.toString()
                val senha = senhaEditText.text.toString()
                val repetirSenha = repetirSenhaEditText.text.toString()
                val tipoUsuario = tipoUsuarioSpinner.selectedItem.toString()

                if (camposEstaoValidos(login, senha, repetirSenha, tipoUsuario)) {
                    try {
                        cadastroViewModel.insertUsuario(
                            Usuario(
                                nome,
                                login,
                                senha,
                                null,
                                telefone,
                                TipoUsuario.from(tipoUsuario)!!
                            )
                        )

                        notificar("Cadastro realizado com sucesso!")
                        findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)

                    } catch (e: Exception) {
                        notificarLongo("Erro: ${e.message}")
                    }
                }
            }
        }

        return binding.root
    }

    private fun camposEstaoValidos(
        login: String,
        senha: String,
        repetirSenha: String,
        tipoUsuario: String,
    ): Boolean {

        if (login.isBlank() || senha.isBlank() || repetirSenha.isBlank()) {
            notificar("Preencha todos os campos!")
            return false
        }

        if (senha != repetirSenha) {
            notificar("As senhas não coincidem!")
            return false
        }

        if (
            TipoUsuario.from(tipoUsuario) == null
        ) {
            notificar("Escolha um usuário.")
            return false
        }

        return true
    }

    private fun notificar(mensagem: String) {
        Toast.makeText(
            requireContext(),
            mensagem,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun notificarLongo(mensagem: String) {
        Toast.makeText(
            requireContext(),
            mensagem,
            Toast.LENGTH_LONG
        ).show()
    }

}
