package com.soojung.a20191228_01_api.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.soojung.a20191214_01_listviewpractice02.adapters.UserAdapter
import com.soojung.a20191228_01_api.BaseActivity
import com.soojung.a20191228_01_api.R
import com.soojung.a20191228_01_api.UserInfoActivity
import com.soojung.a20191228_01_api.data.User
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val userList = ArrayList<User>()
    var mUserAdapter:UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        userListView.setOnItemClickListener { parent, view, position, id ->

            val clickedUser = userList.get(position)

            val intent = Intent(mContext, UserInfoActivity::class.java)
            intent.putExtra("user", clickedUser)
            startActivity(intent)

        }


    }

    override fun setValues() {
        mUserAdapter = UserAdapter(mContext, R.layout.user_list_item, userList)
        userListView.adapter = mUserAdapter

    }

    override fun onResume() {
        super.onResume()

        ConnectServer.getRequestUserList(mContext, object : ConnectServer.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val code = json.getInt("code")

                runOnUiThread {
                   if (code == 200) {

                       userList.clear()

                       val data = json.getJSONObject("data")
                       val users = data.getJSONArray("users")

                       for (i in 0..(users.length() - 1)) {
                           val userJson = users.getJSONObject(i)

                           val userDataObject = User.getUserFromJson(userJson)
                           userList.add(userDataObject)
                       }

                       mUserAdapter?.notifyDataSetChanged()


                   }
                   else {
                    Toast.makeText(mContext, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                   }

                }

            }


        })


    }

}
