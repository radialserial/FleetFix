package com.hinade.fleetfix.model.usuario

import com.hinade.fleetfix.model.usuario.mecanico.Mecanico
import com.hinade.fleetfix.model.usuario.mecanico.MecanicoDao
import com.hinade.fleetfix.model.usuario.motorista.Motorista
import com.hinade.fleetfix.model.usuario.motorista.MotoristaDao
import java.util.Date

class UsuarioRepository(
    private val motoristaDao: MotoristaDao,
    private val mecanicoDao: MecanicoDao
) {

    suspend fun insert(usuario: Usuario) {

        if (mecanicoDao.exists(usuario.login) || motoristaDao.exists(usuario.login)) {
            throw Exception("Usuário já existe.")
        }

        if (usuario.tipoUsuario == TipoUsuario.MOTORISTA) {
            motoristaDao.insert(
                with(usuario) {
                    Motorista(
                        0,
                        nome,
                        login,
                        senha,
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
                        senha,
                        telefone,
                        Date().time
                    )
                }
            )
        }

    }
}
