package com.soojung.a20191228_01_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import com.soojung.a20191228_01_api.data.User
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {



    }

    override fun setValues() {
        val sendUser = intent.getSerializableExtra("user") as User

        userIdTxt.text = sendUser.loginId
        userNameTxt.text = sendUser.name
        userPhoneTxt.text = sendUser.phoneNum
        userMemoTxt.text = sendUser.memo

    }

}
