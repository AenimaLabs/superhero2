package jorge.andaur.rios.superhero2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jorge.andaur.rios.superhero2.databinding.ItemSuperheroBinding
import jorge.andaur.rios.superhero2.model.Superhero

// Adaptador para RecyclerView que muestra una lista de superhéroes
class SuperheroAdapter (private val superheroList: List<Superhero>) : RecyclerView.Adapter<SuperheroAdapter.SuperheroViewHolder>(){

    // ViewHolder para cada superhéroe en la lista
    class SuperheroViewHolder(val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroViewHolder(binding) // Crea una nueva instancia de ViewHolder
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        val superhero = superheroList[position]
        holder.binding.superheroName.text = superhero.name// Asigna el nombre del superhéroe
        Glide.with(holder.binding.root.context).load(superhero.image.url).into(holder.binding.superheroImage)
    }

    override fun getItemCount(): Int {
        return superheroList.size // Retorna el tamaño de la lista de superhéroes
    }

//    class SuperheroViewHolder(val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root) {
//
//
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): SuperheroAdapter.SuperheroViewHolder {
//        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return SuperheroViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: SuperheroAdapter.SuperheroViewHolder, position: Int) {
//        val superhero = superheroList[position]
//        holder.binding.superheroName.text = superhero.name
//        Glide.with(holder.binding.root.context).load(superhero.image.url).into(holder.binding.superheroImage)
//
//    }
//
//    override fun getItemCount(): Int {
//        return superheroList.size
//    }
}




