package com.hinade.fleetfix.model.usuario.mecanico

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hinade.fleetfix.model.usuario.TipoUsuario
import com.hinade.fleetfix.model.usuario.Usuario

@Entity
data class Mecanico(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    override val nome: String,
    override val login: String,
    override val senha: String,
    override val senhaSalt: String,
    override val telefone: String,
    val epochEntrada: Long
) : Usuario(
    nome,
    login,
    senha,
    senhaSalt,
    telefone,
    TipoUsuario.MECANICO
)
