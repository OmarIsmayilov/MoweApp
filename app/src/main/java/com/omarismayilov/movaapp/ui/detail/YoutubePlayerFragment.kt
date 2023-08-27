package com.omarismayilov.movaapp.ui.detail

import android.content.pm.ActivityInfo
import androidx.navigation.fragment.navArgs
import com.omarismayilov.movaapp.common.base.BaseFragment
import com.omarismayilov.movaapp.databinding.FragmentYoutubePlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class YoutubePlayerFragment : BaseFragment<FragmentYoutubePlayerBinding>(FragmentYoutubePlayerBinding::inflate) {

    private val args: YoutubePlayerFragmentArgs by navArgs()

    override fun observeEvents() {}

    override fun onCreateFinish() {
        with(binding) {
            lifecycle.addObserver(ytPlayer)

            ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(args.id, 0f)
                }
            })
        }

    }

    override fun setupListeners() {

    }



}