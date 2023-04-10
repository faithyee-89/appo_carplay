package com.appotronics.carplay_app.page

import com.appotronics.carplay_app.base.BaseActivity
import com.appotronics.carplay_app.databinding.ActivityPicShowBinding
import com.appotronics.carplay_app.ext.setImageDrawableByMipmapString
import com.bumptech.glide.Glide

/**
 * @desc: 启动页
 * @author: yewei
 * @email: 11196649@qq.com
 * @tel: 18680333215
 * @data: 2023/2/16 10:51
 */
class PicShowActivity : BaseActivity<ActivityPicShowBinding>() {

    companion object {
        var TAG = "PicShowActivity"
    }

    override fun init() {
        val bundleExtra = intent.getBundleExtra(TAG)
        val drawableStr = bundleExtra?.getString("pic")

        binding.tvBack.setOnClickListener {
            finish()
        }

        binding.ivArrow.setOnClickListener {
            finish()
        }

//        if(drawableStr != null) {
//            binding.ivPic.setImageDrawableByMipmapString(this, drawableStr)
//        }
        val identifier =
            this.resources.getIdentifier(drawableStr, "mipmap", binding.ivPic.context.packageName)
        Glide.with(this)
            .load(getDrawable(identifier))
            .into(binding.ivPic)
    }
}