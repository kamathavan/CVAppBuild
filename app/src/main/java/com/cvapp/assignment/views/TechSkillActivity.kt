package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.cvapp.assignment.R
import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.ExperienceDataModel
import com.cvapp.assignment.presenter.TechExperiencePresenter
import com.google.firebase.storage.StorageReference

/**
 * Created by Mathavan_K on 7/11/2019.
 * This activity for collecting the technical details
 */

class TechSkillActivity : AppCompatActivity(), PersonalContract.View {

    private val mStorageRef: StorageReference? = null
    private var coreSkill: TextInputEditText? = null
    private var otherSkill: TextInputEditText? = null
    private var txtProfSummary: TextInputEditText? = null
    private var btn: Button? = null
    private val loadBtn: Button? = null
    private val dialog: ProgressDialog? = null
    private val clickListner: UploadProfileContract.ClickListner? = null
    private var ctx: Context? = null
    lateinit var techPresenter: PersonalContract.Presenter
    lateinit var dataModel: ExperienceDataModel
    private var personalData: String? = null
    private var eduData: String? = null

    private val addBtnListerner = View.OnClickListener {
            dataModel!!.coreSkill = coreSkill!!.text.toString()
            dataModel!!.otherSkill = otherSkill!!.text.toString()
            dataModel!!.txtProfSummary = txtProfSummary!!.text.toString()
            techPresenter!!.onSaveBtnClick()
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
        btn = findViewById<View>(R.id.btn_addtech_skill) as Button
        coreSkill = findViewById<View>(R.id.coreSkillTxt) as TextInputEditText
        otherSkill = findViewById<View>(R.id.otherskill) as TextInputEditText
        txtProfSummary = findViewById<View>(R.id.txtProfSummary) as TextInputEditText
        personalData = this.intent.getStringExtra("PersonalInfo")
        eduData = this.intent.getStringExtra("EducationInfo")

        btn!!.setOnClickListener(addBtnListerner)
    }

    override fun onResume() {
        super.onResume()
        dataModel = ExperienceDataModel()
        techPresenter = TechExperiencePresenter(this, dataModel)
    }

    /**
     *  save the personal information data to json file
     */
    override fun savePersonalData(techSkillData:String) {
        val techSkillIntent = Intent(this@TechSkillActivity, ProfessionalExpActivity::class.java)
        techSkillIntent.putExtra("PersonalInfo", personalData)
        techSkillIntent.putExtra("EducationInfo", eduData)
        techSkillIntent.putExtra("TechSkillInfo",techSkillData)
        startActivity(techSkillIntent)
        Log.v("profile--","Profile-->"+personalData+" -"+ eduData +"" +techSkillData);
    }

    /**
     * show error message to fill required fields
     */
    override fun showError() {
        Toast.makeText(applicationContext, resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()

    }
}
