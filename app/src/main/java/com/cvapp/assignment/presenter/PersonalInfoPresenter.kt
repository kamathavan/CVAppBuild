package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.ProfileView
import com.cvapp.assignment.dataobjects.PersonalDataObject
import com.cvapp.assignment.models.PersonalModelInteractor

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class PersonalInfoPresenter(var profileVw: ProfileView) : PersonalModelInteractor.onListener {


    lateinit var personalDO: PersonalDataObject


    fun onSavePersonalInfo(firstName: String, lastName: String, dob: String, emailId: String, city: String, phone: String
                           , nation: String) {
        personalDO = PersonalDataObject(firstName, lastName, phone, dob, city, emailId, nation)
        PersonalModelInteractor().savePersonalInfo(personalDO, this)
    }

    override fun onfailure() {
        profileVw.showError()
    }

    override fun onSuccess(personalData:String) {
        profileVw.savePersonalData(personalData)
    }


}