package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.PersonalDetailModel
import com.cvapp.assignment.utils.Constants.Companion.CITY
import com.cvapp.assignment.utils.Constants.Companion.DOB
import com.cvapp.assignment.utils.Constants.Companion.EMAILID
import com.cvapp.assignment.utils.Constants.Companion.FNAME
import com.cvapp.assignment.utils.Constants.Companion.LNAME
import com.cvapp.assignment.utils.Constants.Companion.NATION
import com.cvapp.assignment.utils.Constants.Companion.PHONE
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class PersonalPresenter(private val view: PersonalContract.View, private val perDataModel: PersonalDetailModel) : PersonalContract.Presenter {
    /**
     * add the personal details
     */

    override fun onSaveBtnClick() {
        val personData = makePersonalInfoJson(perDataModel)
        view.savePersonalData(personData);
    }

    fun isValidFirstAndLastName():Boolean {
        return !perDataModel.firstname.isNullOrBlank() && !perDataModel.firstname.isNullOrBlank()
    }


    fun isValidDob(): Boolean {
        return !perDataModel.dob.isNullOrBlank()
    }

    fun isValidCityAndNation(): Boolean {
        return !perDataModel.nation.isNullOrBlank() && !perDataModel.city.isNullOrBlank ()
    }

    fun isValidEmailId(): Boolean {
        return !perDataModel.emailid.isNullOrBlank()
    }

    fun isValidPhone(): Boolean {
        return !perDataModel.phone.isNullOrEmpty()
    }

    fun isAllFieldOkay(): Boolean {
        return isValidFirstAndLastName() && isValidPhone() && isValidEmailId() && isValidCityAndNation() && isValidDob()
    }

    fun makePersonalInfoJson(perDataModel: PersonalDetailModel): String {
        val jsonPerson = JSONObject()
        try {
            jsonPerson.put(FNAME, perDataModel.firstname)
            jsonPerson.put(LNAME, perDataModel.lastname)
            jsonPerson.put(DOB, perDataModel.dob)
            jsonPerson.put(PHONE, perDataModel.phone)
            jsonPerson.put(EMAILID, perDataModel.emailid)
            jsonPerson.put(CITY, perDataModel.city)
            jsonPerson.put(NATION, perDataModel.nation)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsonPerson.toString()
    }

    override fun isValidateInputField(): Boolean {
        if(isAllFieldOkay()){
            return true
        }else{
            view.showError()
            return false

        }
    }
}
