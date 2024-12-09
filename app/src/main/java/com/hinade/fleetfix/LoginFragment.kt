package com.hinade.fleetfix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hinade.fleetfix.databinding.FragmentLoginBinding

/**
 * Um fragmento representando a tela de login.
 */
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)

        with(binding) {

            loginButton.setOnClickListener {
                if (usuarioEditText.text.isBlank() || senhaEditText.text.isBlank()) {
                    Toast.makeText(
                        requireContext(),
                        "Preencha todos os campos!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Login realizado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            cadastroButton.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_cadastroFragment)
            }

        }

        return binding.root
    }
}
