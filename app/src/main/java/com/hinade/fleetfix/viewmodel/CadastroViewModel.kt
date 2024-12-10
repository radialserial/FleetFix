package com.hinade.fleetfix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hinade.fleetfix.model.AppDatabase
import com.hinade.fleetfix.model.usuario.Usuario
import com.hinade.fleetfix.model.usuario.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel que representa a conexão do
 * [Cadastro][com.hinade.fleetfix.view.fragment.CadastroFragment]
 * com a [base de dados][com.hinade.fleetfix.model.AppDatabase].
 *
 * Acessa as entidades do [Mecânico][com.hinade.fleetfix.model.usuario.mecanico.Mecanico]
 * e do [Motorista][com.hinade.fleetfix.model.usuario.motorista.Motorista].
 */
class CadastroViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioRepository: UsuarioRepository

    init {
        val mecanicoDao = AppDatabase.getInstancia(application).mecanicoDao()
        val motoristaDao = AppDatabase.getInstancia(application).motoristaDao()
        usuarioRepository = UsuarioRepository(motoristaDao, mecanicoDao)
    }

    fun insertUsuario(usuario: Usuario) {
        viewModelScope.launch(Dispatchers.IO) {
            usuarioRepository.insert(usuario)
        }
    }

}
