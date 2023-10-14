package com.santridev.newmovieapp.presentation.movie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.santridev.newmovieapp.base.abstraction.BaseActivity
import com.santridev.newmovieapp.base.extension.loadBackdrop
import com.santridev.newmovieapp.base.extension.loadPoster
import com.santridev.newmovieapp.base.extension.setGone
import com.santridev.newmovieapp.base.extension.setVisible
import com.santridev.newmovieapp.databinding.ActivityMovieDetailBinding
import com.santridev.newmovieapp.domain.model.MovieDetailUiModel
import com.santridev.newmovieapp.domain.resource.MovieDetailResource
import com.santridev.newmovieapp.presentation.movie.detail.genre.GenreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, MovieDetailViewModel>() {
    override val getViewBinding: (LayoutInflater) -> ActivityMovieDetailBinding
        get() = ActivityMovieDetailBinding::inflate
    override val getViewModelClass: Class<MovieDetailViewModel>
        get() = MovieDetailViewModel::class.java

    private val genreAdapter by lazy { GenreAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
        setupView()
        viewModel.getMovieDetail(intent.getIntExtra(EXTRA_MOVIE_ID, 0))
    }

    private fun setupView() {
        binding.genreRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.genreRecyclerView.adapter = genreAdapter
    }

    private fun setupObserver() {
        viewModel.movieDetail.observe(this) {
            when (it) {
                MovieDetailResource.Loading -> {
                    binding.progressBar.setVisible()
                    binding.contentGroup.setGone()
                }

                is MovieDetailResource.Success -> {
                    binding.progressBar.setGone()
                    binding.contentGroup.setVisible()
                    bindUi(it.data)
                }

                is MovieDetailResource.Error -> {
                    binding.progressBar.setGone()
                }
            }
        }
    }

    private fun bindUi(data: MovieDetailUiModel) {
        with(binding) {
            ivBackdrop.loadBackdrop(data.backdropPath)
            tvTitle.text = data.title
            tvReleaseDate.text = data.releaseDate
            tvDuration.text = data.runtime
            ivPoster.loadPoster(data.posterPath)
            genreAdapter.submitList(data.genres)
            tvDescription.text = data.overview
            tvVote.text = data.voteAverage
            tvVoteCount.text = data.voteCount
            tvPopularity.text = data.popularity
        }
    }

    companion object {
        private const val EXTRA_MOVIE_ID = "movieId"

        @JvmStatic
        fun start(context: Context, movieId: Int) {
            val starter = Intent(context, MovieDetailActivity::class.java)
                .putExtra(EXTRA_MOVIE_ID, movieId)
            context.startActivity(starter)
        }
    }
}