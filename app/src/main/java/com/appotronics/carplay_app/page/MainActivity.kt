package com.appotronics.carplay_app.page

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.alibaba.fastjson.JSONObject
import com.appotronics.appo_lib.AppoSDK
import com.appotronics.appo_lib.callback.AppoResponseCallback
import com.appotronics.appo_lib.engine.MqttLogUtils
import com.appotronics.carplay_app.R
import com.appotronics.carplay_app.adapter.AtmosphereAdapter
import com.appotronics.carplay_app.adapter.ImageBottomTitleAdapter
import com.appotronics.carplay_app.base.BaseActivity
import com.appotronics.carplay_app.bean.RequestBean
import com.appotronics.carplay_app.databinding.ActivityMainBinding
import com.appotronics.carplay_app.dialog.PicShowDialog
import com.appotronics.carplay_app.dialog.ShareDialog
import com.appotronics.carplay_app.repo.BannerDataBean
import com.appotronics.carplay_app.utils.ClickUtils
import com.permissionx.guolindev.PermissionX
import com.youth.banner.util.LogUtils

/**
 * @desc: 启动页
 * @author: yewei
 * @email: 11196649@qq.com
 * @tel: 18680333215
 * @data: 2023/2/16 10:51
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private var isShow = false
    lateinit var picShowDialog: PicShowDialog

    override fun init() {
        picShowDialog = PicShowDialog(supportFragmentManager)
        PermissionX.init(this).permissions(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ).request { allGranted, _, _ ->
        }

        binding.ivMap.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        binding.clTravel.setOnClickListener {
//            startActivity(Intent(this, TravelActivity::class.java))
        }

        ClickUtils.applySingleDebouncing(binding.clOrange, View.OnClickListener {
            picShowDialog.show(R.mipmap.ic_1_1)
        })

        ClickUtils.applySingleDebouncing(binding.clGreen, View.OnClickListener {
            picShowDialog.show(R.mipmap.ic_1_1)
        })

        binding.ivShow.setOnClickListener {
            if (!isShow) {
                binding.ivShow.setImageDrawable(getDrawable(R.mipmap.ic_show_close))
                //隐藏所有
                binding.clTopL.visibility = View.GONE
                binding.clTopC.visibility = View.GONE
                binding.clTopR.visibility = View.GONE
                binding.clBottomL.visibility = View.GONE
                binding.clBottomC.visibility = View.GONE
                binding.clBottomR.visibility = View.GONE
            } else {
                binding.ivShow.setImageDrawable(getDrawable(R.mipmap.ic_show_default))
                //显示所有
                binding.clTopL.visibility = View.VISIBLE
                binding.clTopC.visibility = View.VISIBLE
                binding.clTopR.visibility = View.VISIBLE
                binding.clBottomL.visibility = View.VISIBLE
                binding.clBottomC.visibility = View.VISIBLE
                binding.clBottomR.visibility = View.VISIBLE
            }
            isShow = !isShow
        }

        val imageBottomTitleAdapter = ImageBottomTitleAdapter(BannerDataBean.getMainVideoData())
        binding.bannerVideo.setAdapter(imageBottomTitleAdapter)
        binding.bannerVideo.setBannerGalleryEffect(80, 80, 12, 0f)
        binding.bannerVideo.setLoopTime(8000)
        imageBottomTitleAdapter.setOnBannerListener { data, position ->
            startActivity(Intent(this, VideoActivity::class.java))
        }

        val imageAdapter = AtmosphereAdapter(BannerDataBean.getMainAtmosphereData())
        binding.bannerAtmosphere.setAdapter(imageAdapter)
        binding.bannerAtmosphere.setBannerGalleryEffect(60, 60, 12, 0f)
        binding.bannerAtmosphere.setLoopTime(10000)
        AppoSDK.appoResponse(object : AppoResponseCallback {
            override fun response(json: String) {
                MqttLogUtils.i("$json")
                val parseObject = JSONObject.parseObject(json, RequestBean::class.java)
                when (parseObject.type) {
                    "3" -> {
                        //视频
                        startVideoAct("${parseObject.contentName}.mp4")
                    }
                    "2" -> {
                        //图片
//                        startPicAct("ic_${parseObject.contentName}")
                        picShowDialog.show(R.mipmap.ic_1_1)
                    }
                }
            }
        })
    }

    fun startVideoAct(resourceName: String) {
        val intent = Intent(this, VideoPlayActivity::class.java)
        val bundle = Bundle()
        bundle.putString("video", resourceName)
        intent.putExtra(VideoPlayActivity.TAG, bundle)
        startActivity(intent)
    }

    fun startPicAct(drawableStr: String) {
        val intent = Intent(this, PicShowActivity::class.java)
        val bundle = Bundle()
        bundle.putString("pic", drawableStr)
        intent.putExtra(PicShowActivity.TAG, bundle)
        startActivity(intent)
    }

}