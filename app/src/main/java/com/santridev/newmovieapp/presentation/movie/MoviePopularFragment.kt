package com.santridev.newmovieapp.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.santridev.newmovieapp.base.abstraction.BaseFragment
import com.santridev.newmovieapp.base.extension.setGone
import com.santridev.newmovieapp.base.extension.setVisible
import com.santridev.newmovieapp.base.util.EndlessRecyclerOnScrollListener
import com.santridev.newmovieapp.databinding.FragmentMovieBinding
import com.santridev.newmovieapp.domain.model.MovieUiModel
import com.santridev.newmovieapp.domain.resource.MovieResource
import com.santridev.newmovieapp.presentation.movie.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviePopularFragment : BaseFragment<FragmentMovieBinding, MovieViewModel>() {
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
        viewModel.getMoviePopular(1)
    }

    private fun setupObserver() {
        viewModel.moviePopular.observe(viewLifecycleOwner) {
            when (it) {
                MovieResource.Loading -> {
                    binding.progressBar.setVisible()
                    binding.recycleView.setGone()
                }

                is MovieResource.Success -> {
                    binding.progressBar.setGone()
                    binding.recycleView.setVisible()

                    movies.addAll(it.data)
                    adapter.submitList(movies)
                }

                is MovieResource.Error -> {
                    binding.progressBar.setGone()
                }
            }
        }
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = layoutManager

        binding.recycleView.addOnScrollListener(object :
            EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                viewModel.getMoviePopular(current_page)
            }

        })
        adapter.setOnItemMovieClicked { movieId ->
            MovieDetailActivity.start(requireContext(), movieId)
        }
    }
}