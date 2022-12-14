package com.example.youtubeapp.ui.playlist_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.youtubeapp.R

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.youtubeapp.core.network.result.Status
import com.example.youtubeapp.core.ui.BaseActivity
import com.example.youtubeapp.data.remote.model.Item
import com.example.youtubeapp.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapp.ui.playlist.PlaylistActivity
import com.example.youtubeapp.ui.video.VideoActivity
import com.example.youtubeapp.utils.NetworkStatus
import com.example.youtubeapp.utils.NetworkStatusHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailActivity : BaseActivity<ActivityPlaylistDetailBinding, PlaylistDetailViewModel>() {

    private var playlistId: String? = null

    override val viewModel: PlaylistDetailViewModel by viewModel()

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(inflater)
    }

    override fun initView() {
        playlistId = intent.getStringExtra(PlaylistActivity.idPaPda).toString()
        binding.playlistTitle.text = intent.getStringExtra(PlaylistActivity.titlePaPda).toString()
        binding.playlistDescription.text = intent.getStringExtra(PlaylistActivity.descriptionPaPda).toString()
    }

    override fun initListener() {
        super.initListener()
        binding.tvBack.setOnClickListener{
            onBackClick()
        }
    }


    override fun initViewModel() {
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        initVM()
    }

    private fun initRecyclerView(playlistsList: List<Item>) {
        binding.videosRecyclerView.adapter = PlaylistDetailAdapter(playlistsList, this::onItemClick)
    }

    private fun initVM() {
        playlistId?.let {
            viewModel.getPlaylistItems(it).observe(this) {
                when(it.status) {
                    Status.SUCCESS -> {
                        if (it.data != null) {
                            viewModel.loading.postValue(false)
                            initRecyclerView(it.data.items)
                        }
                    }
                    Status.ERROR -> {
                        viewModel.loading.postValue(false)
                        Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        viewModel.loading.postValue(true)
                    }
                }
            }
        }
    }

    private fun checkConnection() {
        NetworkStatusHelper(this).observe(this) {
            if (it == NetworkStatus.Available) {
                binding.detailsMain.visibility = View.VISIBLE
                binding.networkLayout.root.visibility = View.GONE
                initVM()
            } else {
                binding.detailsMain.visibility = View.GONE
                binding.networkLayout.root.visibility = View.VISIBLE
            }
        }
    }

    override fun checkInternet() {
        checkConnection()

        binding.networkLayout.btnTryAgain.setOnClickListener {
            checkConnection()
        }
    }

    private fun onItemClick(channelId: String, videoTitle: String, videoDesc: String) {
        Intent(this, VideoActivity::class.java).apply {
            putExtra(idPdaVa, channelId)
            putExtra(titlePdaVa, videoTitle)
            putExtra(descPdaVa, videoDesc)
            startActivity(this)
        }
    }

    private fun onBackClick(){
        Intent(this, PlaylistActivity::class.java).apply {
            startActivity(this)
        }
    }

    companion object {
        const val idPdaVa = "idPdaVa"
        const val titlePdaVa = "titlePdaVa"
        const val descPdaVa = "descPdaVa"
    }
}