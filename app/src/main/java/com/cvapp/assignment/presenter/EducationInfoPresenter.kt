package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.ProfileView
import com.cvapp.assignment.dataobjects.EducationDataObject
import com.cvapp.assignment.models.EducationModelInteractor

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class EducationInfoPresenter(val view: ProfileView) : EducationModelInteractor.onListener {

    lateinit var educationDo: EducationDataObject;

    fun saveEducation(course: String, university: String, grade: String, yop: String) {
        educationDo = EducationDataObject(university, course, grade, yop);
        EducationModelInteractor().saveEducationInfo(educationDo, this)
    }

    override fun onSuccess(educationData: String) {
        view.savePersonalData(educationData)
    }

    override fun onfailure() {
        view.showError()
    }


}
