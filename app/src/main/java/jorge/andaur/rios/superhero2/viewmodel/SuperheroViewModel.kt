package jorge.andaur.rios.superhero2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jorge.andaur.rios.superhero2.model.Superhero
import jorge.andaur.rios.superhero2.repo.SuperheroRepository
import kotlinx.coroutines.launch

// ViewModel que maneja los datos y la lógica de negocio para los superhéroes
class SuperheroViewModel(private val repository: SuperheroRepository) : ViewModel() {

    // LiveData que expone la lista de superhéroes
    private val _superheroes = MutableLiveData<List<Superhero>>()
    val superheroes: LiveData<List<Superhero>> get() = _superheroes

    private val currentList = mutableListOf<Superhero>()// Lista actual de superhéroes
    private var currentPage = 1// Página actual
    private val pageSize = 10// Tamaño de la página

    init {
        fetchSuperheroes()// Carga la primera página de superhéroes al inicializar el ViewModel
    }

    // Función para obtener superhéroes de la API
    fun fetchSuperheroes() {
        viewModelScope.launch {
            val newSuperheroes = repository.getSuperheroes(currentPage, pageSize)// Obtiene los nuevos superhéroes
            currentList.addAll(newSuperheroes)// Agrega los nuevos superhéroes a la lista actual
            _superheroes.value = currentList// Actualiza el LiveData con la nueva lista
            currentPage++// Incrementa la página actual
        }
    }




//    private val _superhero = MutableLiveData<Superhero>()
//    val superhero: LiveData<Superhero> get() = _superhero



//    fun fetchSuperhero(id: String) {
//
//
//        viewModelScope.launch {
//            try {
//                val result = repository.getSuperhero(id)
//                _superhero.postValue(result)
//            } catch (e: Exception) {
//                // Handle the error
//            }
//        }
//    }
}