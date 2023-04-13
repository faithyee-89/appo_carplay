package com.appotronics.carplay_app.dialog

import androidx.fragment.app.FragmentManager
import com.alibaba.fastjson.JSONObject
import com.appotronics.appo_lib.AppoSDK
import com.appotronics.appo_lib.callback.AppoRequestCallback
import com.appotronics.carplay_app.R
import com.appotronics.carplay_app.base.BaseDialogFragment
import com.appotronics.carplay_app.bean.RequestBean
import com.appotronics.carplay_app.databinding.FragmentPicShowBinding
import com.appotronics.carplay_app.utils.toast.MyPadToastUtils

/**
 * @desc:
 * @author: yewei
 * @data: 2023/4/1 15:26
 */
class PicShowDialog(fragmentManager: FragmentManager) :
    BaseDialogFragment<FragmentPicShowBinding>() {
    private var fragmentManager: FragmentManager
    private var listener: OnItemClickListener? = null
    private var shareDialog: ShareDialog
    private var drawableId: Int? = null

    init {
        this.fragmentManager = fragmentManager
        shareDialog = ShareDialog(this.fragmentManager)
    }

    override fun init() {
        dialog?.window?.setBackgroundDrawable(resources.getDrawable(R.color.transparent))

        binding.ivPic.setImageDrawable(resources.getDrawable(drawableId!!))

        binding.llShare.setOnClickListener {
            val toJSONString = JSONObject.toJSONString(RequestBean().apply {
                contentName = "1"
                url = "1"
                type = "2"
            })
            shareDialog.show(toJSONString, object : ShareDialog.OnItemClickListener {
                override fun onItemClick(
                    viewId: Int,
                    json: String,
                    selectGsnList: MutableList<String>?
                ) {
                    selectGsnList?.forEach { gsn ->
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
                }
            })
        }
    }

    fun show() {
        show(fragmentManager, "PicShowDialog")
    }

    fun show(drawableId: Int) {
        this.drawableId = drawableId
        show()
    }

    interface OnItemClickListener {
        fun onItemClick(viewId: Int, json: String, selectGsnList: MutableList<String>?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}