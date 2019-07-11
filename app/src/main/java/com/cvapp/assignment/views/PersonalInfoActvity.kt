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
import android.widget.TextView
import android.widget.Toast

import com.cvapp.assignment.R
import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.PersonalDetailModel
import com.cvapp.assignment.presenter.PersonalPresenter
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class PersonalInfoActvity : AppCompatActivity(), PersonalContract.View {

    private var mStorageRef: StorageReference? = null
    private var firstName: TextInputEditText? = null
    private var lastName: TextInputEditText? = null
    private var dob: TextInputEditText? = null
    private var phone: TextInputEditText? = null
    private var email: TextInputEditText? = null
    private var city: TextInputEditText? = null
    private var nationality: TextInputEditText? = null
    private var saveBtn: Button? = null
    private val dialog: ProgressDialog? = null
    private var ctx: Context? = null
    lateinit var clickListner: PersonalContract.Presenter
    lateinit var personalDetailModel: PersonalDetailModel

    private val onClickListener = View.OnClickListener {
        personalDetailModel!!.firstname = firstName!!.text.toString()
        personalDetailModel!!.lastname = lastName!!.text.toString()
        personalDetailModel!!.city = city!!.text.toString()
        personalDetailModel!!.nation = nationality!!.text.toString()
        personalDetailModel!!.emailid = email!!.text.toString()
        personalDetailModel!!.phone = phone!!.text.toString()
        personalDetailModel!!.dob = dob!!.text.toString()
        clickListner!!.onSaveBtnClick()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val mTitle = toolbar.findViewById<View>(R.id.toolbar_title) as TextView
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        mTitle.text = "Personal Details"
        ctx = this
        firstName = findViewById<View>(R.id.firstname) as TextInputEditText
        lastName = findViewById<View>(R.id.lastname) as TextInputEditText
        city = findViewById<View>(R.id.cityname) as TextInputEditText
        phone = findViewById<View>(R.id.phone) as TextInputEditText
        email = findViewById<View>(R.id.emailid) as TextInputEditText
        nationality = findViewById<View>(R.id.nationname) as TextInputEditText
        dob = findViewById<View>(R.id.dob) as TextInputEditText
        mStorageRef = FirebaseStorage.getInstance().reference
        saveBtn = findViewById<View>(R.id.btn_add) as Button
        //loadBtn = (Button) findViewById(R.id.load_btn);
        saveBtn!!.setOnClickListener(onClickListener)
    }

    override fun onResume() {
        super.onResume()
        personalDetailModel = PersonalDetailModel()
        clickListner = PersonalPresenter(this, personalDetailModel)

    }

    override fun savePersonalData() {
        val intent = Intent()
        intent.putExtra("Fname", firstName!!.text.toString())
        intent.putExtra("Lname", lastName!!.text.toString())
        intent.putExtra("City", city!!.text.toString())
        intent.putExtra("Nation", nationality!!.text.toString())
        intent.putExtra("EmailId", email!!.text.toString())
        intent.putExtra("Phone", phone!!.text.toString())
        intent.putExtra("Dob", dob!!.text.toString())
        setResult(1, intent)
        finish()
    }

    override fun showError() {
        Toast.makeText(applicationContext, "Please enter all the details....", Toast.LENGTH_LONG).show()

    }
}