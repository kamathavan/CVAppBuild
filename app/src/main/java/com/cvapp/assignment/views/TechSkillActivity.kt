package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.cvapp.assignment.R
import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.ExperienceDataModel
import com.cvapp.assignment.presenter.TechExperiencePresenter
import com.cvapp.assignment.utils.Constants.Companion.EDUCATIONINFO
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import com.cvapp.assignment.utils.Constants.Companion.TECHSKILLINFO
import kotlinx.android.synthetic.main.activity_techskills_screen.*

/**
 * Created by Mathavan_K on 7/11/2019.
 * This activity for collecting the technical details
 */

class TechSkillActivity : AppCompatActivity(), PersonalContract.View {

    lateinit var dialog: ProgressDialog
    lateinit var ctx: Context
    lateinit var techPresenter: PersonalContract.Presenter
    lateinit var expDataModel: ExperienceDataModel
    lateinit var personalInfoData: String
    lateinit var eduInfoData: String

    private val addBtnListerner = View.OnClickListener {
        expDataModel.coreSkill = txtcoreskill.text.toString()
        expDataModel.otherSkill = txttotalexp.text.toString()
        expDataModel.profSummary = txtProfSummary.text.toString()
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
    }

    override fun onResume() {
        super.onResume()
        expDataModel = ExperienceDataModel()
        techPresenter = TechExperiencePresenter(this, expDataModel)
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
