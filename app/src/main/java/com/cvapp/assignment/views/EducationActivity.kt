package com.cvapp.assignment.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.cvapp.assignment.R
import com.cvapp.assignment.contract.ProfileContract
import com.cvapp.assignment.dataobjects.EducationDataObject
import com.cvapp.assignment.presenter.EducationPresenter
import com.cvapp.assignment.utils.Constants.Companion.EDUCATIONINFO
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import kotlinx.android.synthetic.main.activity_education_screen.*

/**
 * Created by Mathavan_K on 7/11/2019.
 */

class EducationActivity : BaseActivity(), ProfileContract.View {

    lateinit var presenter: ProfileContract.Presenter
    lateinit var educationDataObject: EducationDataObject
    internal var personalInfoData: String = ""
    /**
     *  this is for save button click listener
     */
    private val btnEduListener = View.OnClickListener {
        educationDataObject.course = txtcourse.text.toString()
        educationDataObject.grade = txtgrade.text.toString()
        educationDataObject.yop = txtyop.text.toString()
        educationDataObject.univerty = txtboard.text.toString()
        if (presenter.isValidateInputField()) {
            presenter.onSaveBtnClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education_screen)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text = getString(R.string.app_eduinfo_toolbar_title)
        btn_save_edu.setOnClickListener(btnEduListener)
        personalInfoData = this.intent.getStringExtra(PERSONALINFO)
        educationDataObject = EducationDataObject("", "", "", "")
        presenter = EducationPresenter(this, educationDataObject)
    }

    override fun onResume() {
        super.onResume()

    }

    /**
     * Save all the personal information data
     */
    override fun savePersonalData(education: String) {
        val eduIntent = Intent(this@EducationActivity, TechSkillActivity::class.java)
        eduIntent.putExtra(PERSONALINFO, personalInfoData)
        eduIntent.putExtra(EDUCATIONINFO, education)
        startActivity(eduIntent)
    }

    /**
     * show error message to fill all required fields
     */
    override fun showError() {
        Toast.makeText(applicationContext, resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()
    }
}
