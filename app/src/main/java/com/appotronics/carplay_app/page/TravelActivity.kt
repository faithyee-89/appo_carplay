package com.appotronics.carplay_app.page

import android.content.Intent
import android.os.Bundle
import com.alibaba.fastjson.JSONObject
import com.appotronics.appo_lib.AppoSDK
import com.appotronics.appo_lib.callback.AppoRequestCallback
import com.appotronics.carplay_app.base.BaseActivity
import com.appotronics.carplay_app.bean.RequestBean
import com.appotronics.carplay_app.databinding.ActivityTravelBinding
import com.appotronics.carplay_app.dialog.ShareDialog
import com.appotronics.carplay_app.utils.toast.MyPadToastUtils

/**
 * @desc: 启动页
 * @author: yewei
 * @email: 11196649@qq.com
 * @tel: 18680333215
 * @data: 2023/2/16 10:51
 */
class TravelActivity : BaseActivity<ActivityTravelBinding>() {
    lateinit var shareDialog: ShareDialog

    override fun init() {
        shareDialog = ShareDialog(supportFragmentManager)
        binding.ivArrow.setOnClickListener {
            finish()
        }

        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.clTopL.setOnClickListener {
            startPicAct("ic_1_1")
        }

        binding.clTopC.setOnClickListener {
            startPicAct("ic_1_2")
        }

        binding.clTopR.setOnClickListener {
            startPicAct("ic_1_3")
        }

        binding.clBottomL.setOnClickListener {
            startPicAct("ic_1_4")
        }

        binding.clBottomC.setOnClickListener {
            startPicAct("ic_2_1")
        }

        binding.clBottomR.setOnClickListener {
            startPicAct("ic_2_2")
        }

        binding.ivShare1.setOnClickListener {
            sharePic("1")
        }

        binding.ivShare2.setOnClickListener {
            sharePic("2")
        }

        binding.ivShare3.setOnClickListener {
            sharePic("3")
        }

        binding.ivShare4.setOnClickListener {
            sharePic("4")
        }

        binding.ivShare5.setOnClickListener {
            sharePic("5")
        }

        binding.ivShare6.setOnClickListener {
            sharePic("6")
        }
    }

    private fun sharePic(picPath: String) {
        val toJSONString = JSONObject.toJSONString(RequestBean().apply {
            contentName = picPath
            url = picPath
            type = "2"
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

    fun startPicAct(drawableStr: String) {
        var intent = Intent(this, PicShowActivity::class.java)
        var bundle = Bundle()
        bundle.putString("pic", drawableStr)
        intent.putExtra(PicShowActivity.TAG, bundle)
        startActivity(intent)
    }
}