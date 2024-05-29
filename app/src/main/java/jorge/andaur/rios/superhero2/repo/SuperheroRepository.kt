package jorge.andaur.rios.superhero2.repo

import jorge.andaur.rios.superhero2.model.Image
import jorge.andaur.rios.superhero2.model.Superhero
import jorge.andaur.rios.superhero2.network.SuperheroApiService

// Repositorio que maneja la obtención de datos desde la API
class SuperheroRepository(private val apiService: SuperheroApiService) {

  

    // Función suspendida para obtener una lista de superhéroes paginados
    suspend fun getSuperheroes(page: Int, pageSize: Int): List<Superhero> {
        val superheroes = mutableListOf<Superhero>()
        // Calcula el ID inicial y final para la página actual
        val startId = (page - 1) * pageSize + 1
        val endId = startId + pageSize - 1

        // Itera sobre los IDs en el rango de la página
        for (id in startId..endId) {
            val response = apiService.getSuperhero(id.toString())
            // Verifica si la respuesta es exitosa
            if (response.isSuccessful) {
                response.body()?.let {
                    superheroes.add(it)// Agrega el superhéroe a la lista si la respuesta es exitosa
                }
            }
        }

        return superheroes// Retorna la lista de superhéroes
    }
//    suspend fun getSuperhero(id: String): Superhero {
//       return apiService.getSuperhero(id)

//        val superhero = mutableListOf<Superhero>()
//        val startId = (page - 1) * pageSize + 1
//        val endId = startId + pageSize - 1
//
//        for (id in startId..endId) {
//            val response = apiService.getSuperhero(id.toString())
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    superhero.add(it)
//                }
//            }
//        }
//        return superhero
    }
