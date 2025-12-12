package mx.edu.utez.gps.data.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // URL base de la API, que es mockapi o tambien podemos colocar la IPv4 de una computadora
    private const val BASE_URL = "https://693bc40db762a4f15c3e32be.mockapi.io/"

    val apiService: GpsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GpsApiService::class.java)
    }
}