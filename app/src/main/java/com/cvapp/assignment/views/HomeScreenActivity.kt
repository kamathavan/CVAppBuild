package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.cvapp.assignment.R
import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.CloudStorageRepository
import com.cvapp.assignment.presenter.UploadFilePresenter
import com.cvapp.assignment.utils.Constants.Companion.BOARD
import com.cvapp.assignment.utils.Constants.Companion.CITY
import com.cvapp.assignment.utils.Constants.Companion.CORESKILL
import com.cvapp.assignment.utils.Constants.Companion.DOB
import com.cvapp.assignment.utils.Constants.Companion.DURATIONS
import com.cvapp.assignment.utils.Constants.Companion.EDUCATIONS
import com.cvapp.assignment.utils.Constants.Companion.EMAILID
import com.cvapp.assignment.utils.Constants.Companion.FIRSTNAME
import com.cvapp.assignment.utils.Constants.Companion.FNAME
import com.cvapp.assignment.utils.Constants.Companion.GRADE
import com.cvapp.assignment.utils.Constants.Companion.GRADUATE
import com.cvapp.assignment.utils.Constants.Companion.LNAME
import com.cvapp.assignment.utils.Constants.Companion.NATION
import com.cvapp.assignment.utils.Constants.Companion.ORGANIZA
import com.cvapp.assignment.utils.Constants.Companion.ORGANIZATION
import com.cvapp.assignment.utils.Constants.Companion.OTHERSKILL
import com.cvapp.assignment.utils.Constants.Companion.PERSONALDETAILS
import com.cvapp.assignment.utils.Constants.Companion.PHONE
import com.cvapp.assignment.utils.Constants.Companion.PROJECTDETAILS
import com.cvapp.assignment.utils.Constants.Companion.PROJECTS
import com.cvapp.assignment.utils.Constants.Companion.ROLE
import com.cvapp.assignment.utils.Constants.Companion.TOTALEXPERIENCE
import com.cvapp.assignment.utils.Constants.Companion.YOP
import com.cvapp.assignment.utils.DialogUtility
import com.cvapp.assignment.utils.LocalDataStorage

import org.json.JSONException
import org.json.JSONObject

class HomeScreenActivity : AppCompatActivity(), UploadProfileContract.Views {

    private lateinit var btnPersonal: Button
    private lateinit var btnEduction: Button
    private lateinit var btnTech: Button
    private lateinit var btnExp: Button
    private lateinit var saveProfile: Button
    var personalJson: JSONObject? = null
    var educationJson: JSONObject? = null
    var professionJson: JSONObject? = null
    var imgPerson: ImageView ? = null
    var imgEdu: ImageView? = null
    private lateinit var imgProfession: ImageView
    private var dialog: ProgressDialog? = null
    private var clickListner: UploadProfileContract.ClickListner? = null
    lateinit var ctx: Context
    private var isEduOkay = false
    private var isPerOkay = false
    private var isExpOkay = false

    private val personalListern = View.OnClickListener { clickListner!!.onPersonBtnClick() }
    private val educationListern = View.OnClickListener { clickListner!!.onEducationBtnClick() }

    private val techSkillListen = View.OnClickListener { clickListner!!.onExperTechBtnClick() }

    private val saveProfileListener = View.OnClickListener {
        saveAllData()
        val filePath = LocalDataStorage.getInstance(ctx).filePath
        clickListner!!.onButtonClick(filePath)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        mTitle.setText(R.string.app_home)
        ctx = this
        dialog = ProgressDialog(this)
        btnPersonal = findViewById<View>(R.id.btn_personal_details) as Button
        btnPersonal.setOnClickListener(personalListern)
        btnEduction = findViewById<View>(R.id.btn_educa_profile) as Button
        btnEduction.setOnClickListener(educationListern)
        btnTech = findViewById<View>(R.id.btn_techexp_profile) as Button
        btnTech.setOnClickListener(techSkillListen)
        saveProfile = findViewById<View>(R.id.btn_save_profile) as Button
        saveProfile.setOnClickListener(saveProfileListener)
        imgPerson = findViewById<View>(R.id.imgPersonalEnb) as ImageView
        imgEdu = findViewById<View>(R.id.img_edu_icon) as ImageView
        imgProfession = findViewById<View>(R.id.img_techexp_icon) as ImageView

    }

    override fun onResume() {
        super.onResume()
        clickListner = UploadFilePresenter(this@HomeScreenActivity, CloudStorageRepository())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && data != null) {
            builldPersonalDetails(data)
        } else if (requestCode == 2 && data != null) {
            builldEducation(data)
        } else if (requestCode == 3 && data != null) {
            buildProfessionalExp(data)
        }
    }

    /**
     * construct the professional details
     */
    private fun buildProfessionalExp(data: Intent) {
        professionJson = JSONObject()
        try {
            professionJson!!.put(CORESKILL, data.getStringExtra(CORESKILL))
            professionJson!!.put(TOTALEXPERIENCE, data.getStringExtra(OTHERSKILL))
            professionJson!!.put(ROLE, data.getStringExtra(ROLE))
            professionJson!!.put(PROJECTS, data.getStringExtra(PROJECTS))
            professionJson!!.put(DURATIONS, data.getStringExtra(DURATIONS))
            professionJson!!.put(ORGANIZATION, data.getStringExtra(ORGANIZA))
            imgProfession.visibility = View.VISIBLE
            isExpOkay = true
            //saveAllData();
            // LocalDataStorage.getInstance(this).saveData(professionJson.toString());
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
    /**
     * construct the personal details
     */
    private fun builldPersonalDetails(personalIntent: Intent) {
        personalJson = JSONObject()
        try {
            personalJson!!.put(FIRSTNAME, personalIntent.getStringExtra(FNAME))
            personalJson!!.put(LNAME, personalIntent.getStringExtra(LNAME))
            personalJson!!.put(DOB, personalIntent.getStringExtra(DOB))
            personalJson!!.put(CITY, personalIntent.getStringExtra(CITY))
            personalJson!!.put(NATION, personalIntent.getStringExtra(NATION))
            personalJson!!.put(EMAILID, personalIntent.getStringExtra(EMAILID))
            personalJson!!.put(PHONE, personalIntent.getStringExtra(PHONE))
            imgPerson!!.visibility = View.VISIBLE
            isPerOkay = true
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
    /**
     * construct the education details
     */
    private fun builldEducation(personalIntent: Intent) {
        educationJson = JSONObject()
        try {
            educationJson!!.put(BOARD, personalIntent.getStringExtra(BOARD))
            educationJson!!.put(GRADE, personalIntent.getStringExtra(GRADE))
            educationJson!!.put(YOP, personalIntent.getStringExtra(YOP))
            educationJson!!.put(GRADUATE, personalIntent.getStringExtra(GRADUATE))
            imgEdu!!.visibility = View.VISIBLE
            isEduOkay = true
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    /**
     * save all the data to profile
     */
    private fun saveAllData() {
        val alldata = JSONObject()
        try {
            alldata.put(PERSONALDETAILS, personalJson)
            alldata.put(EDUCATIONS, educationJson)
            alldata.put(PROJECTDETAILS, professionJson)
            LocalDataStorage.getInstance(this).saveData(alldata.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    /**
     * show progress file when uploading file
     */
    override fun showProgressDialog(progress: Double) {
        DialogUtility.showLoader(this,progress)
        /*dialog!!.setMessage("Please wait,Uploading file... " + progress.toInt() + "%...")
        dialog!!.show()*/
    }

    /**
     * hide the progress dialog when success or failure
     */
    override fun hideProgressDialog() {
        dialog!!.hide()
        DialogUtility.hideLoader()
    }
    /**
     * show the success dialog when success
     */
    override fun showsuccessMsg() {
        DialogUtility.showSuccessAlert(resources.getString(R.string.app_file_upload),this)
    }
    /**
     * hide the progress dialog when success or failure
     */
    override fun showFailureMsg() {
       Toast.makeText(applicationContext, resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()
    }
    /**
     * navigate to personal details
     */
    override fun navigateToPersonal() {
        val intentRegistration = Intent(this@HomeScreenActivity, PersonalInfoActvity::class.java)
        startActivityForResult(intentRegistration, 1)
    }

    /**
     * navigate to educationdetails
     */
    override fun navigateToEducation() {
        val intentEducation = Intent(this@HomeScreenActivity, EducationActivity::class.java)
        startActivityForResult(intentEducation, 2)
    }
    /**
     * navigate to  the experience details
     */
    override fun navigateToExperience() {
        val techSkillIntent = Intent(this@HomeScreenActivity, TechSkillActivity::class.java)
        startActivityForResult(techSkillIntent, 3)
    }
    /**
     * To enable home button after filled all the sections
     */
    override fun isEnabled(progress: Boolean): Boolean {
        return isEduOkay && isExpOkay && isPerOkay
    }

    /**
     * cleared to the local files
     */
    override fun clearAllData() {
        isEduOkay = false
        imgPerson!!.visibility = View.INVISIBLE
        imgProfession.visibility = View.INVISIBLE
        imgEdu!!.visibility = View.INVISIBLE
        LocalDataStorage.getInstance(this).deleteFileFromLocal()
    }

}
