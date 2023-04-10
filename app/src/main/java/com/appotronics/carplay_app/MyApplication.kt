package com.appotronics.carplay_app

import android.app.Application
import com.appotronics.appo_lib.AppoSDK
import com.appotronics.appo_lib.AuthResult
import com.appotronics.appo_lib.MessageResult
import com.appotronics.appo_lib.callback.AuthResponseCallback
import com.appotronics.appo_lib.engine.MqttLogUtils
import com.appotronics.carplay_app.utils.Utils
import com.appotronics.carplay_app.utils.toast.MyPadToastUtils

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        MyPadToastUtils.setContext(this)
//        9c2f153d-7837-4e29-9b7a-5a2a2e7cc1f1 APPO-000002
//        6d7efc40-a992-46c9-a41f-e29ca3b83ebe APPO-000005
        AppoSDK.init(this, "admin", "public", "APPO-000005", "", object : AuthResponseCallback {
            override fun authRes(result: AuthResult, message: MessageResult, gsn: String) {
                when (result) {
                    AuthResult.AuthSuccess -> {
                        MqttLogUtils.i("connect success,MessageResult is ${message}")
                        MqttLogUtils.i("deviceGsn is ${gsn}")
                    }
                    AuthResult.AuthFail -> {
                        MqttLogUtils.e("connect fail,MessageResult is ${message}")
                    }
                }
            }
        })
    }
}