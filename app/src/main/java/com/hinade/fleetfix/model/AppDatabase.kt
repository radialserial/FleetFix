package com.hinade.fleetfix.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hinade.fleetfix.model.usuario.mecanico.Mecanico
import com.hinade.fleetfix.model.usuario.mecanico.MecanicoDao
import com.hinade.fleetfix.model.usuario.motorista.Motorista
import com.hinade.fleetfix.model.usuario.motorista.MotoristaDao

@Database(
    entities = [
        Mecanico::class,
        Motorista::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mecanicoDao(): MecanicoDao
    abstract fun motoristaDao(): MotoristaDao

    companion object {

        @Volatile
        private var instancia: AppDatabase? = null

        /**
         * Retorna a base de dados como um singleton.
         *
         * Esse método deve ser chamado de dentro de um ViewModel.
         *
         * @param context o contexto da aplicação.
         */
        fun getInstancia(context: Context) = instancia ?: synchronized(this) {
            instancia ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "fleetfix_db"
            ).build().also { instancia = it }
        }
    }

}
