package com.hinade.fleetfix.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hinade.fleetfix.R
import com.hinade.fleetfix.databinding.FragmentLoginBinding
import com.hinade.fleetfix.viewmodel.LoginViewModel

/**
 * Um fragmento representando a tela de login.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        with(binding) {

            loginButton.setOnClickListener {
                if (camposEstaoValidos()) {
                    val login = loginEditText.text.toString()
                    val senha = senhaEditText.text.toString()

                    if (loginViewModel.validarLogin(login, senha)) {
                        notificar("Login realizado com sucesso!")
                    } else {
                        notificar("Dados incorretos.")
                    }
                }
            }

            cadastroButton.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
            }

        }

        return binding.root
    }

    private fun FragmentLoginBinding.camposEstaoValidos(): Boolean {
        if (loginEditText.text.isBlank() || senhaEditText.text.isBlank()) {
            notificar("Preencha todos os campos!")
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

}
