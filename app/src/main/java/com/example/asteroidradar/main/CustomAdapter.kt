package com.example.asteroidradar.main



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.model.Asteroid
import com.example.asteroidradar.R
import com.example.asteroidradar.databinding.AsteroidItemBinding

class CustomAdapter(private val clickListener: AsteroidClickListener) : ListAdapter<Asteroid, CustomAdapter.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<AsteroidItemBinding>(
            inflater, R.layout.asteroid_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!! , clickListener)
    }

    inner class ViewHolder(private val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid , clickListener: AsteroidClickListener) {
            binding.asteroid = asteroid
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid) =
            oldItem.id === newItem.id

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid) =
            oldItem == newItem
    }

}

class AsteroidClickListener(val clickListener: (asteroid : Asteroid) -> Unit){
    fun onClick(asteroid : Asteroid) = clickListener(asteroid)
}

