package com.hinade.fleetfix.model.usuario.mecanico

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MecanicoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(mecanico: Mecanico)

    @Query("SELECT EXISTS(SELECT * FROM mecanico WHERE login = :mecanicoLogin)")
    fun exists(mecanicoLogin: String): Boolean

    @Query("SELECT senhaSalt FROM mecanico WHERE login = :login")
    fun getSenhaSaltByLogin(login: String): String?

    @Query("SELECT EXISTS(SELECT * FROM mecanico WHERE login = :login AND senha = :senhaCriptografada)")
    fun checarSenhaValida(login: String, senhaCriptografada: String): Boolean
}
