package jorge.andaur.rios.superhero2.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jorge.andaur.rios.superhero2.databinding.ActivityMainBinding
import jorge.andaur.rios.superhero2.network.SuperheroApiService
import jorge.andaur.rios.superhero2.repo.SuperheroRepository
import jorge.andaur.rios.superhero2.view.adapter.SuperheroAdapter
import jorge.andaur.rios.superhero2.viewmodel.SuperheroViewModel
import jorge.andaur.rios.superhero2.viewmodel.SuperheroViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// Actividad principal de la aplicación
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Binding para acceder a las vistas
    private val viewModel: SuperheroViewModel by viewModels {
        SuperheroViewModelFactory(SuperheroRepository(
            Retrofit.Builder()
                .baseUrl("https://www.superheroapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SuperheroApiService::class.java)
        ))
    }
    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView() // Configura el RecyclerView
        observeViewModel()// Observa los cambios en el ViewModel

        viewModel.fetchSuperheroes() // Obtiene los superhéroes iniciales
    }

    private fun setupRecyclerView() {
        adapter = SuperheroAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)// Establece el LayoutManager
        binding.recyclerView.adapter = adapter// Asigna el adaptador al RecyclerView

        // Agrega un listener para detectar cuando se llega al final del RecyclerView
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                // Si se llega al final de la lista, obtiene más superhéroes
                if (totalItemCount <= (lastVisibleItem + 2)) {
                    viewModel.fetchSuperheroes()
                }
            }
        })
    }

    private fun observeViewModel() {
        // Observa los cambios en la lista de superhéroes del ViewModel
        viewModel.superheroes.observe(this, Observer { superheroes ->
            adapter = SuperheroAdapter(superheroes)// Actualiza el adaptador con la nueva lista
            binding.recyclerView.adapter = adapter
        })
    }

//    private lateinit var binding: ActivityMainBinding
//    private val viewModel: SuperheroViewModel by viewModels {
//        SuperheroViewModelFactory(
//            SuperheroRepository(
//            Retrofit.Builder()
//                .baseUrl("https://www.superheroapi.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(SuperheroApiService::class.java)
//        )
//        )
//    }
//    private lateinit var adapter: SuperheroAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setupRecyclerView()
//        observeViewModel()
//
//        viewModel.fetchSuperhero("2")
//    }
//
//    private fun setupRecyclerView() {
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//    }
//
//    private fun observeViewModel() {
//        viewModel.superhero.observe(this, Observer { superhero ->
//            adapter = SuperheroAdapter(listOf(superhero))
//            binding.recyclerView.adapter = adapter
//        })
//    }
}