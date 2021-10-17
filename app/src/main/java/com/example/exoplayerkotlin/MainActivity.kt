package com.example.exoplayerkotlin

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.android.synthetic.main.custom_controller_view.*

class MainActivity : AppCompatActivity() {
    var playerView: PlayerView? = null
    var drmLicenseUrl = "https://proxy.uat.widevine.com/proxy?provider=widevine_test"
    var player: SimpleExoPlayer? = null
    var mCtx: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mCtx=this
        changeChannelInitializePlayer()
    }


    private fun changeChannelInitializePlayer() {
        val path = "https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4"
        val uri = Uri.parse(path)
        imgChannelLogo?.let {
            Glide.with(mCtx!!)
                    .load(R.drawable.atv)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(it)
        }
        txtChannelName.text = "ATV"
        txtChannelDesc.text = "Selena"

        val mediaItem = MediaItem.Builder()
                .setUri(uri)
                .setDrmUuid(C.WIDEVINE_UUID)
                .setDrmLicenseUri(drmLicenseUrl)
                .setDrmMultiSession(true)
                .build()
        if (player == null) {
            player = mCtx?.let { SimpleExoPlayer.Builder(it).build() }
            playerView?.player = player
            player?.playWhenReady = true

        }
        player?.setMediaItem(mediaItem)
        player?.prepare()
    }
}