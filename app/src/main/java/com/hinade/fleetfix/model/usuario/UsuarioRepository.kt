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

    fun determinarLogin(login: String, senha: String): TipoUsuario? {
        var tipoUsuario: TipoUsuario?

        val saltSenha =
            mecanicoDao.getSenhaSaltByLogin(login)
                .also { tipoUsuario = TipoUsuario.MECANICO }
                ?: motoristaDao.getSenhaSaltByLogin(login)
                    .also { tipoUsuario = TipoUsuario.MOTORISTA } ?: return null

        val senhaCriptografada = Utils.getSenhaCriptografada(senha, saltSenha)

        when (tipoUsuario) {
            TipoUsuario.MECANICO -> {
                if (mecanicoDao.checarSenhaValida(login, senhaCriptografada)) {
                    return TipoUsuario.MECANICO
                }
                return null
            }

            TipoUsuario.MOTORISTA -> {
                if (motoristaDao.checarSenhaValida(login, senhaCriptografada)) {
                    return TipoUsuario.MOTORISTA
                }
                return null
            }

            else -> {
                return null
            }
        }
    }

}
