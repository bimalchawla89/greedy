package com.example.musicwiki.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicwiki.base.BaseActivity
import com.example.musicwiki.databinding.ActivityDetailBinding
import com.example.musicwiki.extensions.getHtmlSpannedString
import com.example.musicwiki.extensions.gone
import com.example.musicwiki.ui.fragments.GenreFragment
import com.example.musicwiki.utils.Status
import com.example.musicwiki.viewbinding.viewBinding
import com.example.musicwiki.viewmodel.GenreDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : BaseActivity() {
    override val binding: ActivityDetailBinding by viewBinding()
    private val _genreDetailViewModel: GenreDetailViewModel by viewModels()
    private val words = arrayListOf("Albums", "Artists", "Tracks")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent?.extras?.getString("name") ?: ""
        setSupportActionBar(binding.detailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = name
        binding.detailToolbar.setNavigationOnClickListener { finish() }

        _genreDetailViewModel.getGenreDetail(name)

        binding.viewPager.adapter = GenrePagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = words[position]
        }.attach()

        _genreDetailViewModel.genreDetailResultLiveData.observe(this, { result ->
            binding.loading.gone()
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.let {
                        binding.tvGenreName.text = it.tag.name
                        binding.tvGenreDescription.text = getHtmlSpannedString(it.tag.wiki.summary)
                    }
                }
                Status.ERROR -> {
                    // no op
                }
            }
        })
    }

    private inner class GenrePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putString("name", intent?.extras?.getString("name"))
            val fragInfo = GenreFragment()
            fragInfo.arguments = bundle
            return fragInfo
        }
    }
}