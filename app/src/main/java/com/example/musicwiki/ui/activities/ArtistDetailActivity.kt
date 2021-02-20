package com.example.musicwiki.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.example.musicwiki.R
import com.example.musicwiki.adapters.AlbumGenreAdapter
import com.example.musicwiki.adapters.TopArtistAdapter
import com.example.musicwiki.base.BaseActivity
import com.example.musicwiki.databinding.ActivityArtistBinding
import com.example.musicwiki.extensions.getHtmlSpannedString
import com.example.musicwiki.extensions.gone
import com.example.musicwiki.extensions.visible
import com.example.musicwiki.listener.ArtistClickListener
import com.example.musicwiki.listener.GenreClickListener
import com.example.musicwiki.utils.Status
import com.example.musicwiki.viewbinding.viewBinding
import com.example.musicwiki.viewmodel.ArtistDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailActivity : BaseActivity(), GenreClickListener, ArtistClickListener {
    override val binding: ActivityArtistBinding by viewBinding()
    private val _artistDetailViewModel: ArtistDetailViewModel by viewModels()
    private val _genreAdapter by lazy { AlbumGenreAdapter(this) }
    private val _artistAdapter by lazy { TopArtistAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent?.extras?.getString("name") ?: ""

        setSupportActionBar(binding.detailArtistToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = name
        binding.detailArtistToolbar.setNavigationOnClickListener { finish() }

        _artistDetailViewModel.getArtistDetail(name)
        binding.rvArtistGenres.adapter = _genreAdapter
        binding.rvTopArtist.adapter = _artistAdapter

        _artistDetailViewModel.genreAlbumResultLiveData.observe(this, { result ->
            binding.loading.gone()
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.let {
                        binding.tvName.text = it.artist.name
                        binding.tvPlayCount.text =
                            getString(R.string.playcount, it.artist.stats.playcount)
                        binding.tvFollower.text =
                            getString(R.string.follower, it.artist.stats.listeners)
                        binding.tvDescription.text = getHtmlSpannedString(it.artist.bio.summary)
                        binding.ivCover.load(it.artist.image[2].text)
                        binding.tvTopArtist.visible()
                        _genreAdapter.submitList(ArrayList(it.artist.tags.tag))
                        _artistAdapter.submitList(ArrayList(it.artist.similar.artist))
                    }
                }
                Status.ERROR -> {
                    // no op
                }
            }
        })
    }

    override fun genreClicked(name: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
        finish()
    }

    override fun artistClicked(name: String) {
        val intent = Intent(this, ArtistDetailActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
        finish()
    }
}