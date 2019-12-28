package com.soojung.a20191228_01_api.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.soojung.a20191228_01_api.BaseActivity
import com.soojung.a20191228_01_api.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
    }

}
