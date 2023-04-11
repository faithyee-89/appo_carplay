package com.appotronics.carplay_app.page

import android.os.Environment
import android.widget.MediaController
import com.appotronics.carplay_app.base.BaseActivity
import com.appotronics.carplay_app.databinding.ActivityVideoPlayBinding
import com.appotronics.carplay_app.utils.MqttLogUtils
import com.youth.banner.util.LogUtils
import java.io.File

/**
 * @desc: 启动页
 * @author: yewei
 * @email: 11196649@qq.com
 * @tel: 18680333215
 * @data: 2023/2/16 10:51
 */
class VideoPlayActivity : BaseActivity<ActivityVideoPlayBinding>() {
    var isStart = true

    companion object {
        var TAG = "VideoPlayActivity"
    }

    override fun init() {
        val bundleExtra = intent.getBundleExtra(TAG)
        val videoName = bundleExtra?.getString("video")
        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.ivArrow.setOnClickListener {
            finish()
        }

        val videoView = binding.video
        val mediaController = MediaController(this)
//        val uri = "android.resource://" + getPackageName() + "/" + R.raw.play_1
        val videoSdPath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        MqttLogUtils.i("videoSdPath = " + videoSdPath)
        val file = File(videoSdPath, "/$videoName")
        MqttLogUtils.i("videoSdPath2 = " + file.absolutePath)
//        videoView.setVideoURI(Uri.parse(uri))
        videoView.setVideoPath(file.absolutePath)
        mediaController.setMediaPlayer(videoView)
        videoView.start()

        binding.container.setOnClickListener {
            if (isStart) {
                videoView.pause()
            } else {
                videoView.start()
            }
            isStart = !isStart
        }
    }
}