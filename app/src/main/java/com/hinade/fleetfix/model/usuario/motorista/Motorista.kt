package com.hinade.fleetfix.model.usuario.motorista

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hinade.fleetfix.model.usuario.TipoUsuario
import com.hinade.fleetfix.model.usuario.Usuario

@Entity
data class Motorista(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    override val nome: String,
    override val login: String,
    override val senha: String,
    override val telefone: String
) : Usuario(
    nome,
    login,
    senha,
    telefone,
    TipoUsuario.MOTORISTA
)
