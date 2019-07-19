package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cvapp.assignment.R
import com.cvapp.assignment.contract.ProfileView
import com.cvapp.assignment.dataobjects.TechSkillDataObject
import com.cvapp.assignment.presenter.TechExperienceInfoPresenter
import com.cvapp.assignment.utils.Constants.Companion.EDUCATIONINFO
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import com.cvapp.assignment.utils.Constants.Companion.TECHSKILLINFO
import kotlinx.android.synthetic.main.activity_techskills_screen.*

/**
 * Created by Mathavan_K on 7/11/2019.
 * This activity for collecting the technical details
 */

class TechSkillActivity : BaseActivity(), ProfileView {

    lateinit var dialog: ProgressDialog
    lateinit var ctx: Context
    lateinit var expDataObject: TechSkillDataObject
    lateinit var personalInfoData: String
    lateinit var eduInfoData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_techskills_screen)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        mTitle.text = getString(R.string.tech_skill_toolbar_tittle)
        ctx = this
        personalInfoData = this.intent.getStringExtra(PERSONALINFO)
        eduInfoData = this.intent.getStringExtra(EDUCATIONINFO)
        val techPresenter = TechExperienceInfoPresenter(this)
        btn_addtech_skill.setOnClickListener {
            techPresenter.saveTechSkillInfo(
                    txtcoreskill.text.toString(),
                    txttotalexp.text.toString(),
                    txtProfSummary.text.toString()
            )
        }
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
        Log.v("TechSkillData","TechSklll------->"+techSkillData)
    }

    /**
     * show error message to fill required fields
     */
    override fun showError() {
        Toast.makeText(applicationContext, resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()

    }

    override fun showProgress() {

    }

}
