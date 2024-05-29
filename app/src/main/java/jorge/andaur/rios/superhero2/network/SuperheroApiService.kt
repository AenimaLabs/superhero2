package jorge.andaur.rios.superhero2.network

import jorge.andaur.rios.superhero2.model.Superhero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Interfaz de Retrofit para las llamadas a la API de superhéroes
interface SuperheroApiService {
    @GET("api.php/1117243916369294/{id}")
//    suspend fun getSuperhero(@Path("id") id: String): Superhero
    suspend fun getSuperhero(@Path("id") id: String): Response<Superhero>
}