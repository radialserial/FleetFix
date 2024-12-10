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

}
