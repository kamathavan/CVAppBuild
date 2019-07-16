package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import com.cvapp.assignment.R
import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.CloudStorageRepository
import com.cvapp.assignment.models.ProjExperDataModel
import com.cvapp.assignment.presenter.UploadFilePresenter
import com.cvapp.assignment.utils.Constants.Companion.EDUCATIONINFO
import com.cvapp.assignment.utils.Constants.Companion.EXPERIENCEINFO
import com.cvapp.assignment.utils.Constants.Companion.MESSAGE
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import com.cvapp.assignment.utils.Constants.Companion.TECHSKILLINFO
import com.cvapp.assignment.utils.LocalDataStorage
import kotlinx.android.synthetic.main.activity_experience.*
import org.json.JSONException
import org.json.JSONObject

class ProfessionalExpActivity : AppCompatActivity(), UploadProfileContract.Views {

    lateinit var dialog: ProgressDialog
    lateinit var uploadProfilePresenter: UploadProfileContract.ClickListner
    lateinit var ctx: Context
    lateinit var projExpDataModel: ProjExperDataModel
    lateinit var personalInfoData: String
    internal var eduInfoData: String = ""
    internal var techSkillData: String = ""

    private val saveProfileListener = View.OnClickListener {
        projExpDataModel.duration = txtdurafrom.text.toString() + "-" + txtdurafrom.text!!.toString()
        projExpDataModel.organization = txtorganization.text.toString()
        projExpDataModel.role = txtrole.text.toString()
        projExpDataModel.responsibility = txtresponsiblity.text.toString()
        if (uploadProfilePresenter.isValidateInputField()) {
            uploadProfilePresenter.onSaveButtonClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text = "Professional Experience "
        ctx = this
        personalInfoData = this.intent.getStringExtra(PERSONALINFO)
        eduInfoData = this.intent.getStringExtra(EDUCATIONINFO)
        techSkillData = this.intent.getStringExtra(TECHSKILLINFO)
        btn_addtech_skill.setOnClickListener(saveProfileListener)
    }

    override fun onResume() {
        super.onResume()
        projExpDataModel = ProjExperDataModel()
        uploadProfilePresenter = UploadFilePresenter(this@ProfessionalExpActivity, CloudStorageRepository(), projExpDataModel)

    }

    /**
     * show progress dialog when profile is uploaded
     */
    override fun showProgressDialog(progress: Double) {
        dialog = ProgressDialog(this)
        dialog.setMessage(this.resources.getString(R.string.app_loading))
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()
    }

    /**
     * hide the progress bar
     */
    override fun hideProgressDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    /**
     * show success alert after when profile is uploaded to success
     */
    override fun showsuccessMsg() {
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle(MESSAGE)
        builder.setMessage(resources.getString(R.string.app_file_upload))
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog1, id ->
            dialog1.dismiss()
            moveToHome()
        }
        val alert11 = builder.create()
        alert11.show()
    }

    /**
     * show failure alert message when uploaded to failure
     */
    override fun showFailureMsg() {
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle(MESSAGE)
        builder.setMessage(resources.getString(R.string.app_file_upload_failur))
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog1, id ->
            dialog1.dismiss()
            moveToHome()
        }
        val alert11 = builder.create()
        alert11.show()
    }

    /**
     * move to the Home screen
     */
    fun moveToHome() {
        val homeIntent = Intent(this@ProfessionalExpActivity, HomeScreenActivity::class.java)
        homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(homeIntent)
    }

    /**
     *  delete profile the once profile uploaded into the
     *  Firebase Cloud Storage
     */
    override fun clearAllData() {
        LocalDataStorage.getInstance(this).deleteFileFromLocal()
    }

    /**
     *  create the json for store profile into the file and upload it
     *  to the Firebase Cloud Storage
     */
    override fun saveProfile(experienceData: String) {
        val alldata = JSONObject()
        try {
            alldata.put(PERSONALINFO, JSONObject(personalInfoData))
            alldata.put(EDUCATIONINFO, JSONObject(eduInfoData))
            alldata.put(TECHSKILLINFO, JSONObject(techSkillData))
            alldata.put(EXPERIENCEINFO, JSONObject(experienceData))
            LocalDataStorage.getInstance(this).saveData(alldata.toString())
            val path = LocalDataStorage.getInstance(this).filePath
            uploadProfilePresenter.onUploadProfile(path)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    /**
     *  show field validation toast message
     */
    override fun showValidationError() {
        Toast.makeText(applicationContext, this.resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()
    }
}
