package com.ifs.desafiomovies.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ifs.desafiomovies.R
import com.ifs.desafiomovies.databinding.ItemMovieBinding
import com.ifs.desafiomovies.domain.model.Movie

class MovieSimilarListAdapter : PagingDataAdapter<Movie, MovieSimilarListAdapter.ViewHolder>(
    DiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if(movie!= null){
            holder.bind(movie)
        }
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movie) {
            binding.tvMovieName.text = item.title
            binding.tvMovieYear.text = item.release_date
            binding.tvMovieGenres.text = item.genres.toString().replace("[", "").replace("]","")

            Glide.with(binding.root.context)
                .load(item.poster_path)
                .placeholder(R.drawable.photo_load)
                .fallback(R.drawable.broken_image)
                .into(binding.ivMoviePoster)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
}