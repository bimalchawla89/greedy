package com.example.musicwiki.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.musicwiki.R
import com.example.musicwiki.adapters.AlbumAdapter
import com.example.musicwiki.adapters.ArtistAdapter
import com.example.musicwiki.adapters.TrackAdapter
import com.example.musicwiki.databinding.FragmentGenreBinding
import com.example.musicwiki.extensions.gone
import com.example.musicwiki.extensions.visible
import com.example.musicwiki.listener.AlbumCLickListener
import com.example.musicwiki.listener.ArtistClickListener
import com.example.musicwiki.listener.TrackClickListener
import com.example.musicwiki.ui.activities.AlbumDetailActivity
import com.example.musicwiki.ui.activities.ArtistDetailActivity
import com.example.musicwiki.utils.Status
import com.example.musicwiki.viewbinding.viewBinding
import com.example.musicwiki.viewmodel.GenreAlbumViewModel
import com.example.musicwiki.viewmodel.GenreArtistViewModel
import com.example.musicwiki.viewmodel.GenreTrackViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreFragment : Fragment(R.layout.fragment_genre), AlbumCLickListener, ArtistClickListener,
    TrackClickListener {

    private val binding: FragmentGenreBinding by viewBinding()
    private val _genreAlbumsViewModel: GenreAlbumViewModel by viewModels()
    private val _genreArtistsViewModel: GenreArtistViewModel by viewModels()
    private val _genreTracksViewModel: GenreTrackViewModel by viewModels()
    private val _albumAdapter by lazy { AlbumAdapter(this) }
    private val _artistAdapter by lazy { ArtistAdapter(this) }
    private val _trackAdapter by lazy { TrackAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = this.arguments?.getInt("position")
        val name = this.arguments?.getString("name") ?: ""


        when (position) {
            1 -> {
                binding.loading.visible()
                _genreArtistsViewModel.getGenreArtists(name)
            }
            2 -> {
                binding.loading.visible()
                _genreTracksViewModel.getGenreTracks(name)
            }
            else -> {
                binding.loading.visible()
                _genreAlbumsViewModel.getGenreAlbums(name)
            }
        }

        _genreArtistsViewModel.genreArtistsResultLiveData.observe(viewLifecycleOwner, { result ->
            binding.loading.gone()
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.let {
                        binding.rvGenreDetail.adapter = _artistAdapter
                        _artistAdapter.submitList(it.topArtists.artist)
                    }
                }
                Status.ERROR -> {
                    // no op
                }
            }
        })
        _genreAlbumsViewModel.genreAlbumResultLiveData.observe(viewLifecycleOwner, { result ->
            binding.loading.gone()
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.let {
                        binding.rvGenreDetail.adapter = _albumAdapter
                        _albumAdapter.submitList(it.albums.album)
                    }
                }
                Status.ERROR -> {
                    // no op
                }
            }
        })
        _genreTracksViewModel.genreTracksResultLiveData.observe(viewLifecycleOwner, { result ->
            binding.loading.gone()
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.let {
                        binding.rvGenreDetail.adapter = _trackAdapter
                        _trackAdapter.submitList(it.tracks.track)
                    }
                }
                Status.ERROR -> {
                    // no op
                }
            }
        })
    }

    override fun albumClicked(name: String, artist: String) {
        val intent = Intent(context, AlbumDetailActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("artist", artist)
        startActivity(intent)
    }

    override fun artistClicked(name: String) {
        val intent = Intent(context, ArtistDetailActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }

    override fun trackClicked(name: String) {
        // no op
    }
}