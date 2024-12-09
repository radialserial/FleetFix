package com.hinade.fleetfix.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hinade.fleetfix.R
import com.hinade.fleetfix.databinding.FragmentCadastroBinding
import com.hinade.fleetfix.model.TipoUsuario

/**
 * Um fragmento representando a tela de cadastro.
 */
class CadastroFragment : Fragment() {

    private lateinit var binding: FragmentCadastroBinding

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
                if (
                    usuarioEditText.text.isBlank() ||
                    senhaEditText.text.isBlank() ||
                    repetirSenhaEditText.text.isBlank()
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Preencha todos os campos!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (senhaEditText.text.toString() != repetirSenhaEditText.text.toString()) {
                    Toast.makeText(
                        requireContext(),
                        "As senhas não coincidem!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (
                    TipoUsuario.from(tipoUsuarioSpinner.selectedItem.toString()) == null
                ) {
                    Toast.makeText(
                        requireContext(),
                        "Escolha um usuário.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Cadastro realizado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)
                }
            }
        }

        return binding.root
    }
}
