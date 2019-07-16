package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.cvapp.assignment.R
import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.CloudStorageRepository
import com.cvapp.assignment.models.ProjExperDataModel
import com.cvapp.assignment.presenter.UploadFilePresenter
import com.cvapp.assignment.utils.Constants.Companion.EDUCATIONINFO
import com.cvapp.assignment.utils.Constants.Companion.EXPERIENCEINFO
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import com.cvapp.assignment.utils.Constants.Companion.TECHSKILLINFO
import com.cvapp.assignment.utils.DialogUtility
import com.cvapp.assignment.utils.LocalDataStorage
import com.google.firebase.storage.StorageReference
import org.json.JSONException
import org.json.JSONObject

class ProfessionalExpActivity : AppCompatActivity(), UploadProfileContract.Views {

    private val mStorageRef: StorageReference? = null
    private var organEdit: TextInputEditText? = null
    private var roleEdit: TextInputEditText? = null
    private var respoEdit: TextInputEditText? = null
    private var duraFromEdit: TextInputEditText? = null
    private var duraToEdit: TextInputEditText? = null
    private var btn: Button? = null
    private val loadBtn: Button? = null
    private var dialog: ProgressDialog? = null
    private var clickListner: UploadProfileContract.ClickListner? = null
    private var ctx: Context? = null
    private val techPresenter: PersonalContract.Presenter? = null
    var dataModel: ProjExperDataModel? = null
    var personalData: String = ""
    internal var eduData: String = ""
    internal var techSkillData: String = ""

    private val saveProfileListener = View.OnClickListener {
        dataModel!!.duration = duraFromEdit!!.text!!.toString() + "-" + duraToEdit!!.text!!.toString()
        dataModel!!.organization = organEdit!!.text!!.toString()
        dataModel!!.role = roleEdit!!.text!!.toString()
        dataModel!!.responsibility = respoEdit!!.text!!.toString()
        if(clickListner!!.isValidateInputField()){
            clickListner!!.onSaveButtonClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        mTitle.text = "Professional Experience "
        ctx = this
        btn = findViewById<View>(R.id.btn_addtech_skill) as Button
        organEdit = findViewById<View>(R.id.organization) as TextInputEditText
        roleEdit = findViewById<View>(R.id.role) as TextInputEditText
        respoEdit = findViewById<View>(R.id.responsiblity) as TextInputEditText
        duraFromEdit = findViewById<View>(R.id.duraFrom) as TextInputEditText
        duraToEdit = findViewById<View>(R.id.toDuration) as TextInputEditText
        personalData = this.intent.getStringExtra(PERSONALINFO)
        eduData = this.intent.getStringExtra(EDUCATIONINFO)
        techSkillData = this.intent.getStringExtra(TECHSKILLINFO)
        btn!!.setOnClickListener(saveProfileListener)
    }

    override fun onResume() {
        super.onResume()
        dataModel = ProjExperDataModel()
        clickListner = UploadFilePresenter(this@ProfessionalExpActivity, CloudStorageRepository(), dataModel!!)

    }

    override fun showProgressDialog(progress: Double) {
        dialog = ProgressDialog(this)
        dialog!!.setMessage(this.resources.getString(R.string.app_loading))
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(false)
        dialog!!.show()
    }

    override fun hideProgressDialog() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

    override fun showsuccessMsg() {
        val builder = AlertDialog.Builder(ctx!!)
        builder.setTitle("Message")
        builder.setMessage(resources.getString(R.string.app_file_upload))
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog1, id ->
            dialog1.dismiss()
            moveToHome()
        }
        val alert11 = builder.create()
        alert11.show()
    }

    override fun showFailureMsg() {
        val builder = AlertDialog.Builder(ctx!!)
        builder.setTitle("Message")
        builder.setMessage(resources.getString(R.string.app_file_upload_failur))
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog1, id ->
            dialog1.dismiss()
            moveToHome()
        }
        val alert11 = builder.create()
        alert11.show()
    }


    fun moveToHome() {
        val homeIntent = Intent(this@ProfessionalExpActivity, HomeScreenActivity::class.java)
        homeIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(homeIntent)
    }


    override fun clearAllData() {
        LocalDataStorage.getInstance(this).deleteFileFromLocal()
    }

    override fun saveProfile(experienceData: String) {
        val alldata = JSONObject()
        try {
            alldata.put(PERSONALINFO, JSONObject(personalData))
            alldata.put(EDUCATIONINFO, JSONObject(eduData))
            alldata.put(TECHSKILLINFO, JSONObject(techSkillData))
            alldata.put(EXPERIENCEINFO, JSONObject(experienceData))
            LocalDataStorage.getInstance(this).saveData(alldata.toString())
            val path = LocalDataStorage.getInstance(this).filePath
            clickListner!!.onUploadProfile(path)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
    override fun showValidationError() {
        Toast.makeText(applicationContext, this.resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()    }
}
