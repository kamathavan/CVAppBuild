package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.cvapp.assignment.R
import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.PersonalDetailData
import com.cvapp.assignment.models.PersonalDetailModel
import com.cvapp.assignment.presenter.PersonalPresenter
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_home_screen.*

class PersonalInfoActvity : AppCompatActivity(), PersonalContract.View {

    lateinit var mStorageRef: StorageReference
    lateinit var dialog: ProgressDialog
    lateinit var ctx: Context
    lateinit var personalPresenter: PersonalContract.Presenter
    lateinit var personalDetailModel: PersonalDetailModel
    /**
     * When click on save button add the personal
     * info into the json file
     */
    private val onClickListener = View.OnClickListener {
        personalDetailModel.firstname = txtfirstname.text.toString()
        personalDetailModel.lastname = txtlastname.text.toString()
        personalDetailModel.city = txtcityname.text.toString()
        personalDetailModel.nation = txtnationname.text.toString()
        personalDetailModel.emailid = txtemailid.text.toString()
        personalDetailModel.phone = txtphone.text.toString()
        personalDetailModel.dob = txtdob.text.toString()
        if (personalPresenter.isValidateInputField()) {
            personalPresenter.onSaveBtnClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar_title.text = "Personal Details"
        ctx = this
        mStorageRef = FirebaseStorage.getInstance().reference
        btn_add.setOnClickListener(onClickListener)
    }

    override fun onResume() {
        super.onResume()
        personalDetailModel = PersonalDetailModel()
        personalPresenter = PersonalPresenter(this, personalDetailModel)

    }

    /**
     * save personal data into storage
     * json file and move to education UI
     */
    override fun savePersonalData(data: String) {
        val personalIntent = Intent(this@PersonalInfoActvity, EducationActivity::class.java)
        personalIntent.putExtra(PERSONALINFO, data)
        startActivity(personalIntent)

    }

    /**
     * show error message when required
     * personinfo field missing to fill
     */
    override fun showError() {
        Toast.makeText(applicationContext, resources.getString(R.string.app_field_validation_msg), Toast.LENGTH_LONG).show()

    }
}
