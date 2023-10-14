package com.santridev.newmovieapp.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentPagerAdapter
import com.santridev.newmovieapp.base.abstraction.BaseActivityBinding
import com.santridev.newmovieapp.databinding.ActivityMainBinding
import com.santridev.newmovieapp.presentation.movie.MovieNowPlayingFragment
import com.santridev.newmovieapp.presentation.movie.MoviePopularFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityBinding<ActivityMainBinding>() {
    override val getViewBinding: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        val adapter = ViewPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        adapter.addFragment(MovieNowPlayingFragment(), "Now Playing")
        adapter.addFragment(MoviePopularFragment(), "Popular Movie")
//        adapter.addFragment(MovieNowPlayingFragment(), "Your Favorite")
        binding.viewPager.adapter = adapter
    }
}
