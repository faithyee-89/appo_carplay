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
    private var selectGsn = ""

    init {
        this.fragmentManager = fragmentManager
        this.selectItem = 0
    }

    override fun init() {
        dialog?.window?.setBackgroundDrawable(resources.getDrawable(R.color.transparent))
        val window = dialog?.window
        val attributes = window?.attributes
        attributes?.dimAmount = 0.0f
        window?.attributes = attributes

        binding.clWindow.setOnClickListener {
            changeRadio(1)
            selectItem = 1
            selectGsn = "9c2f153d-7837-4e29-9b7a-5a2a2e7cc1fe"
        }

        binding.clCar.setOnClickListener {
            changeRadio(2)
            selectItem = 2
//            selectGsn = "a56cae0f-3ca5-42ab-8b85-e191a86c038c" //APPO-000004
            selectGsn = "07dc7ae9-8189-4fca-9ba1-d998a7705ef7" //方兵
        }

        binding.rlSure.setOnClickListener {
            listener?.onItemClick(it.id, this.json, selectGsn)
            dismiss()
        }

    }

    fun changeRadio(cursor: Int) {
        when (cursor) {
            1 -> {
                binding.rbWindow.background = resources.getDrawable(R.mipmap.ic_checkbox_check)
                binding.rbCar.background = resources.getDrawable(R.mipmap.ic_checkbox_uncheck)
            }
            2 -> {
                binding.rbWindow.background = resources.getDrawable(R.mipmap.ic_checkbox_uncheck)
                binding.rbCar.background = resources.getDrawable(R.mipmap.ic_checkbox_check)
            }
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
        fun onItemClick(viewId: Int, json: String, gsn: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}