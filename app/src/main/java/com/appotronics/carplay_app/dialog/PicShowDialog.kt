package com.appotronics.carplay_app.dialog

import androidx.fragment.app.FragmentManager
import com.appotronics.carplay_app.R
import com.appotronics.carplay_app.base.BaseDialogFragment
import com.appotronics.carplay_app.databinding.FragmentPicShowBinding

/**
 * @desc:
 * @author: yewei
 * @data: 2023/4/1 15:26
 */
class PicShowDialog(fragmentManager: FragmentManager) :
    BaseDialogFragment<FragmentPicShowBinding>() {
    private var fragmentManager: FragmentManager
    private var listener: OnItemClickListener? = null
    private var shareDialog : ShareDialog
    private var drawableId : Int? = null


    init {
        this.fragmentManager = fragmentManager
        shareDialog = ShareDialog(this.fragmentManager)
    }

    override fun init() {
        dialog?.window?.setBackgroundDrawable(resources.getDrawable(R.color.transparent))
//        val window = dialog?.window
//        val attributes = window?.attributes
//        attributes?.dimAmount = 0.0f
//        window?.attributes = attributes

        binding.ivPic.setImageDrawable(resources.getDrawable(drawableId!!))

        binding.llShare.setOnClickListener {
            shareDialog.show()
        }
    }

    fun show() {
        show(fragmentManager, "PicShowDialog")
    }

    fun show(drawableId : Int) {
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