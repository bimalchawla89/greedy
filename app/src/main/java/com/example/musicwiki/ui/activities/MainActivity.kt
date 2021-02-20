package com.example.musicwiki.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.musicwiki.R
import com.example.musicwiki.adapters.GenreAdapter
import com.example.musicwiki.base.BaseActivity
import com.example.musicwiki.databinding.ActivityMainBinding
import com.example.musicwiki.extensions.gone
import com.example.musicwiki.listener.GenreClickListener
import com.example.musicwiki.models.Tag
import com.example.musicwiki.utils.Status
import com.example.musicwiki.viewbinding.viewBinding
import com.example.musicwiki.viewmodel.GenreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), GenreClickListener {
    override val binding: ActivityMainBinding by viewBinding()
    private val _genreViewModel: GenreViewModel by viewModels()
    private val _genreAdapter by lazy { GenreAdapter(this) }
    private var genreList = listOf<Tag>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvGenres.adapter = _genreAdapter

        binding.tvExpand.setOnClickListener {
            if (binding.tvExpand.text == getString(R.string.expand)) {
                binding.tvExpand.text = getString(R.string.collapse)
                binding.tvExpand.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up, 0)
                _genreAdapter.submitList(ArrayList(genreList))
            } else {
                binding.tvExpand.text = getString(R.string.expand)
                binding.tvExpand.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.ic_down,
                    0
                )
                _genreAdapter.submitList(ArrayList(genreList.take(10)))
            }
        }

        _genreViewModel.genreResultLiveData.observe(this, { result ->
            binding.loading.gone()
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.let {
                        genreList = it.toptags.tag
                        _genreAdapter.submitList(ArrayList(genreList.take(10)))
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
    }
}