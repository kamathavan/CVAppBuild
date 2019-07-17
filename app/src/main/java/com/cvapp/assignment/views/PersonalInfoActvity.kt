package com.cvapp.assignment.views

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.cvapp.assignment.R
import com.cvapp.assignment.contract.ProfileContract
import com.cvapp.assignment.dataobjects.PersonalDataObject
import com.cvapp.assignment.presenter.PersonalPresenter
import com.cvapp.assignment.utils.Constants.Companion.PERSONALINFO
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_home_screen.*

class PersonalInfoActvity : BaseActivity(), ProfileContract.View {

    lateinit var mStorageRef: StorageReference
    lateinit var dialog: ProgressDialog
    lateinit var ctx: Context
    lateinit var profilePresenter: ProfileContract.Presenter
    lateinit var personalDataObject: PersonalDataObject

    /**
     * When click on save button add the personal
     * info into the json file
     */
    private val onClickListener = View.OnClickListener {
        personalDataObject.firstname = txtfirstname.text.toString()
        personalDataObject.lastname = txtlastname.text.toString()
        personalDataObject.city = txtcityname.text.toString()
        personalDataObject.nation = txtnationname.text.toString()
        personalDataObject.emailid = txtemailid.text.toString()
        personalDataObject.phone = txtphone.text.toString()
        personalDataObject.dob = txtdob.text.toString()
        if (profilePresenter.isValidateInputField()) {
            profilePresenter.onSaveBtnClick()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_title.text = getString(R.string.personal_info_toolbar_title)
        ctx = this
        mStorageRef = FirebaseStorage.getInstance().reference
        btn_add.setOnClickListener(onClickListener)
        personalDataObject = PersonalDataObject("", "", "", "", "", "", "")
        profilePresenter = PersonalPresenter(this, personalDataObject)
    }

    override fun onResume() {
        super.onResume()
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
