package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import com.cvapp.assignment.R
import com.cvapp.assignment.contract.ProfileView
import com.cvapp.assignment.presenter.PersonalInfoPresenter
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_home_screen.*

class PersonalInfoActvity : BaseActivity(),ProfileView {


    lateinit var mStorageRef: StorageReference
    lateinit var dialog: ProgressDialog
    lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_title.text = getString(R.string.personal_info_toolbar_title)
        ctx = this
        mStorageRef = FirebaseStorage.getInstance().reference
        var persenter = PersonalInfoPresenter(this)
        btn_add.setOnClickListener {
            persenter.onSavePersonalInfo(
                    txtfirstname.text.toString(),
                    txtfirstname.text.toString(),
                    txtdob.text.toString(),
                    txtemailid.text.toString(),
                    txtcityname.text.toString(),
                    txtphone.text.toString(),
                    txtnationname.text.toString()
            )
        }

    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * save the professional info data
     */
    override fun savePersonalData(data: String) {
        val personalIntent = Intent(this@PersonalInfoActvity, EducationActivity::class.java)
        personalIntent.putExtra(PERSONALINFO, data)
        startActivity(personalIntent)
    }
    /**
     * show error message when required
     * personinfo field missing to fill
     */
    override fun showError() {
        Toast.makeText(applicationContext, resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        Log.v("","--PersonalData----->");
    }
}
