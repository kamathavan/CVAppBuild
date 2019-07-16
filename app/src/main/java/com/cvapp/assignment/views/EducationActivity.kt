package com.cvapp.assignment.views

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
import com.cvapp.assignment.models.EducationDataModel
import com.cvapp.assignment.presenter.EducationPresenter
import com.cvapp.assignment.utils.Constants.Companion.EDUCATIONINFO
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO

/**
 * Created by Mathavan_K on 7/11/2019.
 */

class EducationActivity : AppCompatActivity(), PersonalContract.View {

    private var boardEdi: TextInputEditText? = null
    private var degreeEdt: TextInputEditText? = null
    private var yopEdt: TextInputEditText? = null
    private var gradeEdt: TextInputEditText? = null
    private var btn_save_edu: Button? = null
    lateinit var presenter: PersonalContract.Presenter
    lateinit var educationDataModel: EducationDataModel
    internal var personalData: String = ""

    /**
     *  this is for save button click listener
     */
    private val btnEduLister = View.OnClickListener {
        educationDataModel!!.mDescipline = degreeEdt!!.text.toString()
        educationDataModel!!.mGrade = gradeEdt!!.text.toString()
        educationDataModel!!.yop = yopEdt!!.text.toString()
        educationDataModel!!.mUniverty = boardEdi!!.text.toString()
        if (presenter.isValidateInputField()) {
            presenter!!.onSaveBtnClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education_screen)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        mTitle.text = "Education Details"
        btn_save_edu = findViewById<View>(R.id.btn_save_edu) as Button
        btn_save_edu!!.setOnClickListener(btnEduLister)
        boardEdi = findViewById<View>(R.id.board) as TextInputEditText
        degreeEdt = findViewById<View>(R.id.degreename) as TextInputEditText
        yopEdt = findViewById<View>(R.id.yop) as TextInputEditText
        gradeEdt = findViewById<View>(R.id.grade) as TextInputEditText
        personalData = this.intent.getStringExtra(PERSONALINFO)

    }

    override fun onResume() {
        super.onResume()
        educationDataModel = EducationDataModel()
        presenter = EducationPresenter(this, educationDataModel)
    }

    /**
     * Save all the personal information data
     */
    override fun savePersonalData(education: String) {
        val eduIntent = Intent(this@EducationActivity, TechSkillActivity::class.java)
        eduIntent.putExtra(PERSONALINFO, personalData)
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
