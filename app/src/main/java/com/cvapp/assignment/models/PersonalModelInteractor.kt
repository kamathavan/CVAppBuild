package com.cvapp.assignment.models

import com.cvapp.assignment.dataobjects.PersonalDataObject
import com.google.gson.Gson
import org.json.JSONObject

/**
 * Created by Mathavan_K on 7/17/2019.
 */
class PersonalModelInteractor {
    lateinit var personalString:String
    interface onListener {
        fun onSuccess(person:String)
        fun onfailure()
    }

    fun savePersonalInfo(pesonalDataObject: PersonalDataObject, onListener: onListener) {
        if (!pesonalDataObject.firstname.isNullOrBlank()
                && !pesonalDataObject.lastname.isNullOrBlank()
                && !pesonalDataObject.dob.isNullOrBlank()
                && !pesonalDataObject.emailid.isNullOrBlank()
                && !pesonalDataObject.phone.isNullOrBlank()
                && !pesonalDataObject.city.isNullOrBlank()
                && !pesonalDataObject.nation.isNullOrBlank()) {
            val gsonObj = Gson();
            personalString = gsonObj.toJson(pesonalDataObject).toString();
            onListener.onSuccess(personalString)
        } else {
            onListener.onfailure()
        }
    }
}