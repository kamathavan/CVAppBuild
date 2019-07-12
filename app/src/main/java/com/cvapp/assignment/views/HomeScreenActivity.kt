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
        setSupportActionBar(toolbar)
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
            professionJson!!.put("CoreSkill", data.getStringExtra("CoreSkill"))
            professionJson!!.put("Total Experience", data.getStringExtra("OtherSkill"))
            professionJson!!.put("Role", data.getStringExtra("Role"))
            professionJson!!.put("Project", data.getStringExtra("Projects"))
            professionJson!!.put("Durations", data.getStringExtra("Durations"))
            professionJson!!.put("Organization", data.getStringExtra("Organiza"))
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
            personalJson!!.put("Firstname", personalIntent.getStringExtra("Fname"))
            personalJson!!.put("Lname", personalIntent.getStringExtra("Lname"))
            personalJson!!.put("Dob", personalIntent.getStringExtra("Dob"))
            personalJson!!.put("City", personalIntent.getStringExtra("City"))
            personalJson!!.put("Nation", personalIntent.getStringExtra("Nation"))
            personalJson!!.put("EmailId", personalIntent.getStringExtra("EmailId"))
            personalJson!!.put("Phone", personalIntent.getStringExtra("Phone"))
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
            educationJson!!.put("Board", personalIntent.getStringExtra("Board"))
            educationJson!!.put("Grade", personalIntent.getStringExtra("Grade"))
            educationJson!!.put("Yop", personalIntent.getStringExtra("Yop"))
            educationJson!!.put("Graduate", personalIntent.getStringExtra("Graduate"))
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
            alldata.put("PersonalDetails", personalJson)
            alldata.put("Educations", educationJson)
            alldata.put("ProjectDetails", professionJson)
            LocalDataStorage.getInstance(this).saveData(alldata.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    /**
     * show progress file when uploading file
     */
    override fun showProgressDialog(progress: Double) {
        dialog!!.setMessage("Please wait,Uploading file... " + progress.toInt() + "%...")
        dialog!!.show()
    }

    /**
     * hide the progress dialog when success or failure
     */
    override fun hideProgressDialog() {
        dialog!!.hide()
    }
    /**
     * show the success dialog when success
     */
    override fun showsuccessMsg() {
        Toast.makeText(applicationContext, "Your profile is uploaded successfully....", Toast.LENGTH_LONG).show()
    }
    /**
     * hide the progress dialog when success or failure
     */
    override fun showFailureMsg() {
        Toast.makeText(applicationContext, "Please fill all the details", Toast.LENGTH_LONG).show()
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
        setContentView(R.layout.activity_home_screen)
    }

}
