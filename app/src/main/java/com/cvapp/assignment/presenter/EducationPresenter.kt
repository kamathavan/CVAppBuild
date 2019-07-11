package com.cvapp.assignment.presenter

import android.support.design.widget.TextInputEditText

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.EducationDataModel

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class EducationPresenter(private val view: PersonalContract.View, private val eduDataModel: EducationDataModel) : PersonalContract.Presenter {

    /**
     *  add the educations details to profile
     */
    override fun onSaveBtnClick() {
        if (!eduDataModel.getmDescipline()!!.isEmpty() && !eduDataModel.getmGrade()!!.isEmpty() &&
                !eduDataModel.yop!!.isEmpty() && !eduDataModel.getmUniverty()!!.isEmpty()) {
            view.savePersonalData()
        } else {
            view.showError()
        }

    }
}