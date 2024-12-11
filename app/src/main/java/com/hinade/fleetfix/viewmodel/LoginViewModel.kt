package com.hinade.fleetfix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hinade.fleetfix.model.AppDatabase
import com.hinade.fleetfix.model.usuario.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * ViewModel que representa a conexão do
 * [Login][com.hinade.fleetfix.view.fragment.LoginFragment]
 * com a [base de dados][com.hinade.fleetfix.model.AppDatabase].
 *
 * Acessa as entidades do [Mecânico][com.hinade.fleetfix.model.usuario.mecanico.Mecanico]
 * e do [Motorista][com.hinade.fleetfix.model.usuario.motorista.Motorista].
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioRepository: UsuarioRepository

    init {
        val mecanicoDao = AppDatabase.getInstancia(application).mecanicoDao()
        val motoristaDao = AppDatabase.getInstancia(application).motoristaDao()
        usuarioRepository = UsuarioRepository(motoristaDao, mecanicoDao)
    }

    fun validarLogin(login: String, senha: String): Boolean {
        return runBlocking(Dispatchers.IO) {
            usuarioRepository.validarLogin(login, senha)
        }
    }

}
