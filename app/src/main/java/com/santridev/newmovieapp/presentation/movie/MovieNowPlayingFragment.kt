package com.santridev.newmovieapp.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.santridev.newmovieapp.base.abstraction.BaseFragment
import com.santridev.newmovieapp.base.util.EndlessRecyclerOnScrollListener
import com.santridev.newmovieapp.databinding.FragmentMovieBinding
import com.santridev.newmovieapp.domain.model.MovieUiModel
import com.santridev.newmovieapp.presentation.movie.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieNowPlayingFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>() {
    override val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieBinding
        get() = FragmentMovieBinding::inflate

    override val getViewModelClass: Class<MovieViewModel>
        get() = MovieViewModel::class.java

    private val adapter by lazy { MovieAdapter() }
    private var movies = mutableListOf<MovieUiModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver()
        setupView()
        viewModel.getMovieNowPlaying(1)
    }

    private fun setupObserver() {
        viewModel.movieNowPlaying.observe(viewLifecycleOwner) {
            movies.addAll(it)
            adapter.submitList(movies)
        }
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = layoutManager

        binding.recycleView.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                viewModel.getMovieNowPlaying(current_page)
            }

        })
        adapter.setOnItemMovieClicked { movieId ->
            MovieDetailActivity.start(requireContext(), movieId)
        }
    }
}