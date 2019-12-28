package com.soojung.a20191228_01_api

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.soojung.a20191228_01_api.data.User
import com.soojung.a20191228_01_api.utils.ConnectServer
import com.soojung.a20191228_01_api.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_my_profile.*
import org.json.JSONObject

class MyProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

//        로그아웃 버튼이 눌리면
//        로그아웃 확인 (제목) / 정말 로그아웃 하시겠습니까? (내용)
//        확인=> 토스트로 로그아웃 / 취소=> 아무일도X, 버튼 보유
//        사용자 의사 확인

        logoutBtn.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃 확인")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(mContext, "로그아웃", Toast.LENGTH_SHORT).show()

                ContextUtil.setUserToken(mContext, "")

                val intent = Intent(mContext, LoginActivity::class.java)
                startActivity(intent)

                finish()




            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }


    }

    override fun setValues() {

        ConnectServer.getRequestMyInfo(mContext, object :ConnectServer.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                Log.d("내정보서버응답", json.toString())

                val code = json.getInt("code")

                runOnUiThread {


                if (code == 200){
                    val data = json.getJSONObject("data")
                    val user = data.getJSONObject("user")

                    val loginUser = User.getUserFromJson(user)

                    nameTxt.text = loginUser.name
                    phoneTxt.text = loginUser.phoneNum
                    loginTxt.text = loginUser.loginId

                }
                else{
                    Toast.makeText(mContext, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                }

                }
            }


        })

    }

}
