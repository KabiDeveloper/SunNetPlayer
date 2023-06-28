package com.kabiplayer.screens

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.kabiplayer.R
import com.kabiplayer.constants.RandomURLPicker
import com.kabiplayer.databinding.ActivityExoplayerBinding
import com.kabiplayer.extensions.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExoVideoPlayActivity : BaseActivity() {
    lateinit var binding: ActivityExoplayerBinding

    private var playbackPosition = 0L

    private var exoPlayer: ExoPlayer? = null
    private lateinit var imageViewFullScreen: ImageView
    private lateinit var imageViewLock: ImageView
    private lateinit var linearLayoutControlUp: LinearLayout
    private lateinit var linearLayoutControlBottom: LinearLayout
    private var playWhenReady = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setView()
        setFindViewById()
        preparePlayer()
        setLockScreen()
        setFullScreen()

    }


    private fun setView() {
        binding = ActivityExoplayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    private fun setFindViewById() {
        imageViewFullScreen = findViewById(R.id.imageViewFullScreen)
        imageViewLock = findViewById(R.id.imageViewLock)
        linearLayoutControlUp = findViewById(R.id.linearLayoutControlUp)
        linearLayoutControlBottom = findViewById(R.id.linearLayoutControlBottom)

    }

    private fun preparePlayer() {

        exoPlayer = ExoPlayer.Builder(this).setSeekBackIncrementMs(INCREMENT_MILLIS)
            .setSeekForwardIncrementMs(INCREMENT_MILLIS)
            .build()

        //  exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer?.let { exo ->
            exo.playWhenReady = true
            binding.playerView.player = exo
            exo.setMediaSource(setMediaType())
            exo.seekTo(playbackPosition)
            exo.playWhenReady = playWhenReady
            exo.prepare()
        }


    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.release()
            exoPlayer = null
        }
    }

    private fun setMediaType(): MediaSource {
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val url = RandomURLPicker.getRandomString()

        val mediaItem = MediaItem.fromUri(url)
        return ProgressiveMediaSource.Factory(defaultHttpDataSourceFactory)
            .createMediaSource(mediaItem)
    }

    private fun lockScreen(lock: Boolean) {
        if (lock) {
            linearLayoutControlUp.visibility = View.INVISIBLE
            linearLayoutControlBottom.visibility = View.INVISIBLE
        } else {
            linearLayoutControlUp.visibility = View.VISIBLE
            linearLayoutControlBottom.visibility = View.VISIBLE
        }
    }

    private fun setLockScreen() {
        imageViewLock.setOnClickListener {
            if (!isLock) {
                imageViewLock.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_lock
                    )
                )
            } else {
                imageViewLock.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_lock_open
                    )
                )
            }
            isLock = !isLock
            lockScreen(isLock)
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun setFullScreen() {
        imageViewFullScreen.setOnClickListener {

            if (!isFullScreen) {
                imageViewFullScreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_fullscreen_exit
                    )
                )
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                imageViewFullScreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_baseline_fullscreen
                    )
                )
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            isFullScreen = !isFullScreen
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (isLock) return
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageViewFullScreen.performClick()
        } else super.onBackPressed()
    }


    override fun onStop() {
        super.onStop()
        exoPlayer?.stop()
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer?.pause()
        releasePlayer()
    }

    companion object {
        private var isFullScreen = false
        private var isLock = false
        private const val INCREMENT_MILLIS = 5000L

    }


}







