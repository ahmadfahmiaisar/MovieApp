package com.santridev.newmovieapp.presentation.movie.detail.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.santridev.newmovieapp.databinding.ItemGenreBinding
import com.santridev.newmovieapp.domain.model.MovieDetail

class GenreAdapter : ListAdapter<MovieDetail.Genre, GenreAdapter.GenreViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(layoutInflater, parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GenreViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetail.Genre) {
            binding.tvGenre.text = item.name
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<MovieDetail.Genre>() {
            override fun areItemsTheSame(
                oldItem: MovieDetail.Genre,
                newItem: MovieDetail.Genre
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieDetail.Genre,
                newItem: MovieDetail.Genre
            ): Boolean = oldItem == newItem

        }
    }
}