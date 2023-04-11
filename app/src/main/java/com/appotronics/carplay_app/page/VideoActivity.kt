package com.appotronics.carplay_app.page

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.alibaba.fastjson.JSONObject
import com.appotronics.appo_lib.AppoSDK
import com.appotronics.appo_lib.callback.AppoRequestCallback
import com.appotronics.carplay_app.R
import com.appotronics.carplay_app.adapter.ImageTitleAdapter
import com.appotronics.carplay_app.base.BaseActivity
import com.appotronics.carplay_app.bean.RequestBean
import com.appotronics.carplay_app.databinding.ActivityVideoBinding
import com.appotronics.carplay_app.dialog.ShareDialog
import com.appotronics.carplay_app.repo.BannerDataBean
import com.appotronics.carplay_app.utils.toast.MyPadToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.RectangleIndicator
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.util.BannerUtils

/**
 * @desc: 启动页
 * @author: yewei
 * @email: 11196649@qq.com
 * @tel: 18680333215
 * @data: 2023/2/16 10:51
 */
class VideoActivity : BaseActivity<ActivityVideoBinding>() {
    lateinit var shareDialog: ShareDialog

    override fun init() {
        shareDialog = ShareDialog(supportFragmentManager)
        val imageAdapter = ImageTitleAdapter(BannerDataBean.getMainVideoData())
        binding.bannerVideo.apply {
            setAdapter(imageAdapter)
            setIndicator(RectangleIndicator(this@VideoActivity))
            setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
            setIndicatorMargins(
                IndicatorConfig.Margins(
                    0,
                    0,
                    BannerUtils.dp2px(35.5f),
                    BannerUtils.dp2px(14f)
                )
            )
            setLoopTime(8000)
        }
        imageAdapter.setOnItemShareClickListener {
            shareVideo(it.title)
        }
        imageAdapter.setOnBannerListener(object : OnBannerListener<BannerDataBean> {
            override fun OnBannerClick(data: BannerDataBean, position: Int) {
                startVideoAct("${data.title}.mp4")
            }
        })

        showRoundPic(binding.ivItem1, R.mipmap.ic_main_video_1)
        showRoundPic(binding.ivItem2, R.mipmap.ic_main_video_2)
        showRoundPic(binding.ivItem3, R.mipmap.ic_main_video_3)
        showRoundPic(binding.ivItem4, R.mipmap.ic_main_video_4)


        binding.ivArrow.setOnClickListener {
            finish()
        }

        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.ivShare.setOnClickListener {
            shareDialog.show()
        }

        binding.llItem1.setOnClickListener {
            startVideoAct("沸雪首钢光影秀.mp4")
        }

        binding.llItem2.setOnClickListener {
            startVideoAct("故宫上元之夜.mp4")
        }

        binding.llItem3.setOnClickListener {
            startVideoAct("产品视频.mp4")
        }

        binding.llItem4.setOnClickListener {
            startVideoAct("光峰品牌宣传片.mp4")
        }

        binding.ivShare1.setOnClickListener {
            shareVideo("沸雪首钢光影秀")
        }

        binding.ivShare2.setOnClickListener {
            shareVideo("故宫上元之夜")
        }

        binding.ivShare3.setOnClickListener {
            shareVideo("产品视频")
        }

        binding.ivShare4.setOnClickListener {
            shareVideo("光峰品牌宣传片")
        }
    }

    private fun shareVideo(videoName: String) {
        val toJSONString = JSONObject.toJSONString(RequestBean().apply {
            contentName = videoName
            url = videoName
            type = "3"
        })
        shareDialog.show(toJSONString, object : ShareDialog.OnItemClickListener {
            override fun onItemClick(viewId: Int, json: String, gsn: String) {
                AppoSDK.appoRequest(
                    json,
                    gsn,
                    object : AppoRequestCallback {
                        override fun onFail() {

                        }

                        override fun onSuccess() {
                            MyPadToastUtils.showShortToast("分享成功")
                        }
                    })
            }
        })
    }

    private fun startVideoAct(resourceName: String) {
        val intent = Intent(this, VideoPlayActivity::class.java)
        val bundle = Bundle()
        bundle.putString("video", resourceName)
        intent.putExtra(VideoPlayActivity.TAG, bundle)
        startActivity(intent)
    }

    fun showRoundPic(imageView: ImageView, drawableId: Int) {
        val roundedCorners = RoundedCorners(10)
        val options = RequestOptions.bitmapTransform(roundedCorners)
        options.transform(CenterCrop(), roundedCorners)
        Glide.with(imageView)
            .load(drawableId)
            .apply(options)
            .into(imageView)
    }
}