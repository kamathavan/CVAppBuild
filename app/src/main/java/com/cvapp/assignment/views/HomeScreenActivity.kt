package com.cvapp.assignment.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import com.cvapp.assignment.R
import kotlinx.android.synthetic.main.activity_profile.*

class HomeScreenActivity : BaseActivity() {

    lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.setText(R.string.app_home)
        ctx = this
        btn_save_profile.setOnClickListener(saveProfileListener)
    }

   private  val saveProfileListener = View.OnClickListener {
        val intent = Intent(this@HomeScreenActivity, PersonalInfoActvity::class.java)
        startActivity(intent)
    }



}
