package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cvapp.assignment.R
import com.cvapp.assignment.contract.ProfileContract
import com.cvapp.assignment.dataobjects.TechSkillDataObject
import com.cvapp.assignment.presenter.TechExperiencePresenter
import com.cvapp.assignment.utils.Constants.Companion.EDUCATIONINFO
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import com.cvapp.assignment.utils.Constants.Companion.TECHSKILLINFO
import kotlinx.android.synthetic.main.activity_techskills_screen.*

/**
 * Created by Mathavan_K on 7/11/2019.
 * This activity for collecting the technical details
 */

class TechSkillActivity : BaseActivity(), ProfileContract.View {

    lateinit var dialog: ProgressDialog
    lateinit var ctx: Context
    lateinit var techPresenter: ProfileContract.Presenter
    lateinit var expDataObject: TechSkillDataObject
    lateinit var personalInfoData: String
    lateinit var eduInfoData: String

    private val addBtnListerner = View.OnClickListener {
        expDataObject.coreSkill = txtcoreskill.text.toString()
        expDataObject.otherSkill = txttotalexp.text.toString()
        expDataObject.profSummary = txtProfSummary.text.toString()
        if (techPresenter.isValidateInputField()) {
            techPresenter!!.onSaveBtnClick()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_techskills_screen)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        mTitle.text = "Technical Skills"
        ctx = this
        personalInfoData = this.intent.getStringExtra(PERSONALINFO)
        eduInfoData = this.intent.getStringExtra(EDUCATIONINFO)
        btn_addtech_skill.setOnClickListener(addBtnListerner)
        expDataObject = TechSkillDataObject("", "", "")
        techPresenter = TechExperiencePresenter(this, expDataObject)
    }

    override fun onResume() {
        super.onResume()

    }

    /**
     *  save the personal information data to json file
     */
    override fun savePersonalData(techSkillData: String) {
        val techSkillIntent = Intent(this@TechSkillActivity, ProfessionalExpActivity::class.java)
        techSkillIntent.putExtra(PERSONALINFO, personalInfoData)
        techSkillIntent.putExtra(EDUCATIONINFO, eduInfoData)
        techSkillIntent.putExtra(TECHSKILLINFO, techSkillData)
        startActivity(techSkillIntent)
    }

    /**
     * show error message to fill required fields
     */
    override fun showError() {
        Toast.makeText(applicationContext, resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()

    }
}
