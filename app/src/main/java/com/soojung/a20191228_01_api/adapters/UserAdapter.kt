package com.soojung.a20191214_01_listviewpractice02.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.soojung.a20191228_01_api.R
import com.soojung.a20191228_01_api.data.User

class UserAdapter(context:Context, resId:Int, list:ArrayList<User>)
    : ArrayAdapter<User>(context, resId, list) {

    val  mContext = context
    val  mList = list
    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.user_list_item, null)
        }
        val row = tempRow!!

        val data = mList.get(position)

        val nameTxt = row.findViewById<TextView>(R.id.nameTxt)
        val phoneNumeTxt = row.findViewById<TextView>(R.id.phoneNumTxt)

        nameTxt.text = data.name
        phoneNumeTxt.text = "(${data.phoneNum})"


        return row

    }
}