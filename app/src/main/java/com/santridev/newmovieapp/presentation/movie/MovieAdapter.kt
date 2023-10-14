package com.santridev.newmovieapp.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.santridev.newmovieapp.base.extension.loadPoster
import com.santridev.newmovieapp.databinding.ItemMovieBinding
import com.santridev.newmovieapp.domain.model.MovieUiModel

class MovieAdapter :
    ListAdapter<MovieUiModel, MovieAdapter.MovieViewHolder>(diffCallBack) {
    private var onItemMovieClicked: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), onItemMovieClicked)
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieUiModel, onItemMovieClicked: ((Int) -> Unit)?) {
            with(binding) {
                ivPoster.loadPoster(item.posterPath)
                tvTitle.text = item.title
                tvRating.text = item.voteAverage
                tvReleaseDate.text = item.releaseDate

                root.setOnClickListener { onItemMovieClicked?.invoke(item.id) }
            }
        }
    }

    fun setOnItemMovieClicked(itemClicked: (Int) -> Unit) {
        this.onItemMovieClicked = itemClicked
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<MovieUiModel>() {
            override fun areItemsTheSame(
                oldItem: MovieUiModel,
                newItem: MovieUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieUiModel,
                newItem: MovieUiModel
            ): Boolean = oldItem == newItem

        }
    }
}