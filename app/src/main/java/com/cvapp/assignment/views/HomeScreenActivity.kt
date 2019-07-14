package com.cvapp.assignment.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.cvapp.assignment.R

class HomeScreenActivity : AppCompatActivity() {

    private var saveProfile: Button? = null
    private var ctx: Context? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        mTitle.setText(R.string.app_home)
        ctx = this
        saveProfile = findViewById<View>(R.id.btn_save_profile) as Button
        saveProfile!!.setOnClickListener(saveProfileListener)
    }

    private val saveProfileListener = View.OnClickListener {
        val intent = Intent(this@HomeScreenActivity, PersonalInfoActvity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

    }


}
