package mx.edu.utez.gps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mx.edu.utez.gps.data.db.Trip
import mx.edu.utez.gps.data.repository.TripRepository

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TripRepository(application)

    // Usaremos un contador para forzar la actualización del Flow.
    private val refreshTrigger = MutableStateFlow(0)

    // Flujo de datos que reacciona al "trigger" para volver a pedir los datos
    val completedTrips: StateFlow<List<Trip>> =
        refreshTrigger.flatMapLatest {
            // Cada vez que 'refreshTrigger' cambia, este bloque se re-ejecuta.
            repository.getAllCompletedTrips()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun deleteTrip(trip: Trip) {
        viewModelScope.launch {
            repository.deleteTrip(trip.id)
            // Incrementamos el contador para disparar la actualización.
            refreshTrigger.value++
        }
    }
}