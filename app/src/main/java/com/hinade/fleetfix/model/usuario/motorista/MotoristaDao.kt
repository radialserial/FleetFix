package com.hinade.fleetfix.model.usuario.motorista

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MotoristaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(motorista: Motorista)

    @Query("SELECT EXISTS (SELECT * from motorista WHERE login=:motoristaLogin)")
    suspend fun exists(motoristaLogin: String): Boolean

    @Query("SELECT senhaSalt FROM motorista WHERE login = :login")
    fun getSenhaSaltByLogin(login: String): String?

    @Query("SELECT EXISTS(SELECT * FROM motorista WHERE login = :login AND senha = :senhaCriptografada)")
    fun checarSenhaValida(login: String, senhaCriptografada: String): Boolean

}
