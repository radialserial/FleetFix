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
}
