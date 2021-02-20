package com.example.musicwiki.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.example.musicwiki.adapters.AlbumGenreAdapter
import com.example.musicwiki.base.BaseActivity
import com.example.musicwiki.databinding.ActivityAlbumBinding
import com.example.musicwiki.extensions.getHtmlSpannedString
import com.example.musicwiki.extensions.gone
import com.example.musicwiki.listener.GenreClickListener
import com.example.musicwiki.utils.Status
import com.example.musicwiki.viewbinding.viewBinding
import com.example.musicwiki.viewmodel.AlbumDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailActivity : BaseActivity(), GenreClickListener {
    override val binding: ActivityAlbumBinding by viewBinding()
    private val _albumDetailViewModel: AlbumDetailViewModel by viewModels()
    private val _genreAdapter by lazy { AlbumGenreAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent?.extras?.getString("name") ?: ""
        val artist = intent?.extras?.getString("artist") ?: ""

        setSupportActionBar(binding.detailAlbumToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = name
        binding.detailAlbumToolbar.setNavigationOnClickListener { finish() }

        binding.rvAlbumGenres.adapter = _genreAdapter

        _albumDetailViewModel.getAlbumDetail(name, artist)

        _albumDetailViewModel.genreAlbumResultLiveData.observe(this, { result ->
            binding.loading.gone()
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.let {
                        binding.tvName.text = it.album.name
                        binding.tvArtist.text = it.album.artist
                        binding.tvDescription.text = getHtmlSpannedString(it.album.wiki.summary)
                        binding.ivCover.load(it.album.image[2].text)
                        _genreAdapter.submitList(ArrayList(it.album.tags.tag))
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
}