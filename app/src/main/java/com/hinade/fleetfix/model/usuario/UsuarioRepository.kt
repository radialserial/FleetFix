package com.hinade.fleetfix.model.usuario

import com.hinade.fleetfix.model.usuario.mecanico.Mecanico
import com.hinade.fleetfix.model.usuario.mecanico.MecanicoDao
import com.hinade.fleetfix.model.usuario.motorista.Motorista
import com.hinade.fleetfix.model.usuario.motorista.MotoristaDao
import com.hinade.fleetfix.util.Utils
import java.security.SecureRandom
import java.util.Date

class UsuarioRepository(
    private val motoristaDao: MotoristaDao,
    private val mecanicoDao: MecanicoDao
) {

    @OptIn(ExperimentalStdlibApi::class)
    suspend fun insert(usuario: Usuario) {

        if (mecanicoDao.exists(usuario.login) || motoristaDao.exists(usuario.login)) {
            throw Exception("Usuário já existe.")
        }

        // Criptografando a senha
        val saltBytes = ByteArray(16)
        SecureRandom().nextBytes(saltBytes)

        val saltString = saltBytes.toHexString()

        val senhaCriptografada = Utils.getSenhaCriptografada(
            usuario.senha,
            saltString
        )

        if (usuario.tipoUsuario == TipoUsuario.MOTORISTA) {
            motoristaDao.insert(
                with(usuario) {
                    Motorista(
                        0,
                        nome,
                        login,
                        senhaCriptografada,
                        saltString,
                        telefone
                    )
                }
            )
        } else {
            mecanicoDao.insert(
                with(usuario) {
                    Mecanico(
                        0,
                        nome,
                        login,
                        senhaCriptografada,
                        saltBytes.toHexString(),
                        telefone,
                        Date().time
                    )
                }
            )
        }

    }

    fun validarLogin(login: String, senha: String): Boolean {
        var tipoUsuario: TipoUsuario

        val saltSenha =
            mecanicoDao.getSenhaSaltByLogin(login)
                .also { tipoUsuario = TipoUsuario.MECANICO }
                ?: motoristaDao.getSenhaSaltByLogin(login)
                    .also { tipoUsuario = TipoUsuario.MOTORISTA } ?: return false

        val senhaCriptografada = Utils.getSenhaCriptografada(senha, saltSenha)

        return if (tipoUsuario == TipoUsuario.MECANICO) {
            mecanicoDao.checarSenhaValida(login, senhaCriptografada)
        } else {
            motoristaDao.checarSenhaValida(login, senhaCriptografada)
        }
    }
}
