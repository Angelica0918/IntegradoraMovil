package mx.edu.utez.gps.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true)
    // Para que retrofit asigne el id que viene del server mockapi
    var id: Long = 0,

    val startTime: Long = System.currentTimeMillis(),
    var endTime: Long? = null,
    var photoUri: String? = null,
    var title: String = "Sin Título",

    // Campo de distancia (Punto 2)
    var distance: Double = 0.0
)