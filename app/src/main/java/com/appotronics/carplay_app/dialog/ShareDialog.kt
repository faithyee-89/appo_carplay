package com.appotronics.carplay_app.dialog

import androidx.fragment.app.FragmentManager
import com.appotronics.carplay_app.R
import com.appotronics.carplay_app.base.BaseDialogFragment
import com.appotronics.carplay_app.databinding.FragmentShareBinding

/**
 * @desc:
 * @author: yewei
 * @data: 2023/4/1 15:26
 */
class ShareDialog(fragmentManager: FragmentManager) : BaseDialogFragment<FragmentShareBinding>() {
    private var fragmentManager: FragmentManager
    private var selectItem: Int
    private var listener: OnItemClickListener? = null
    private var json = ""
    private var selectGsnList: MutableList<String>? = null
    private var selectA = false
    private var selectFront = false


    init {
        this.fragmentManager = fragmentManager
        this.selectItem = 0
        selectGsnList = mutableListOf()
    }

    override fun init() {
        dialog?.window?.setBackgroundDrawable(resources.getDrawable(R.color.transparent))
        val window = dialog?.window
        val attributes = window?.attributes
        attributes?.dimAmount = 0.0f
        window?.attributes = attributes

        binding.clWindow.setOnClickListener { //前车
            //"a56cae0f-3ca5-42ab-8b85-e191a86c038c" //APPO-000004
            val shareGsn = "35256911-5d8e-45f0-9521-182e79c1830c" //方兵
            if (!selectFront) {
                selectGsnList?.add(shareGsn)
                binding.ivCarFront.setImageDrawable(resources.getDrawable(R.mipmap.ic_share_car_circle_select))
            } else {
                selectGsnList?.remove(shareGsn)
                binding.ivCarFront.setImageDrawable(resources.getDrawable(R.mipmap.ic_share_car_circle))
            }

            selectFront = !selectFront

        }

        binding.clCar.setOnClickListener {  //A车
            val shareGsn = "9c2f153d-7837-4e29-9b7a-5a2a2e7cc1fe"
            if (!selectA) {
                selectGsnList?.add(shareGsn)
                binding.ivCarA.setImageDrawable(resources.getDrawable(R.mipmap.ic_share_car_circle_select))
            } else {
                selectGsnList?.remove(shareGsn)
                binding.ivCarA.setImageDrawable(resources.getDrawable(R.mipmap.ic_share_car_circle))
            }
            selectA = !selectA
        }

        binding.rlSure.setOnClickListener {
            listener?.onItemClick(it.id, this.json, selectGsnList)
            dismiss()
        }

    }

    fun show() {
        show(fragmentManager, "ShareDialog")
    }

    fun show(json: String, onItemClickListener: OnItemClickListener) {
        this.json = json
        this.listener = onItemClickListener
        show(fragmentManager, "ShareDialog")
    }

    interface OnItemClickListener {
        fun onItemClick(viewId: Int, json: String, selectGsnList: MutableList<String>?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}