package com.hinade.fleetfix.model.usuario

import androidx.room.Ignore

/**
 * Classe genérica que representa um usuário.
 *
 * @property nome o nome completo do usuário.
 * @property login o e-mail do usuário.
 * @property senhaSalt o "salt" utilizado para reforçar a criptografia da senha.
 * @property tipoUsuario representa o tipo do usuário.
 * ([Mecânico][com.hinade.fleetfix.model.usuario.mecanico.Mecanico]
 * ou [Motorista][com.hinade.fleetfix.model.usuario.motorista.Motorista])
 * apenas durante a execução da aplicação.
 */
open class Usuario(
    open val nome: String,
    open val login: String,
    open val senha: String,
    open val senhaSalt: String?,
    open val telefone: String,
    @Ignore val tipoUsuario: TipoUsuario
)