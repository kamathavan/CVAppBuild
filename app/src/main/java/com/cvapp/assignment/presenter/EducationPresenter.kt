package com.cvapp.assignment.presenter

import android.support.design.widget.TextInputEditText

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.EducationDataModel
import com.cvapp.assignment.utils.Constants.Companion.BOARD
import com.cvapp.assignment.utils.Constants.Companion.GRADE
import com.cvapp.assignment.utils.Constants.Companion.GRADUATE
import com.cvapp.assignment.utils.Constants.Companion.YOP
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class EducationPresenter(private val view: PersonalContract.View, private val eduDataModel: EducationDataModel) : PersonalContract.Presenter {
    override fun isValidateInputField(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     *  add the educations details to profile
     */
    override fun onSaveBtnClick() {
        if (isAllDataFieldOkay()) {
            val eduData = makeEducationJson(eduDataModel)
            view.savePersonalData(eduData)
        } else {
            view.showError()
        }
    }

    fun makeEducationJson(eduDataModel: EducationDataModel): String {
        val educationJson = JSONObject()
        try {
            educationJson.put(GRADUATE, eduDataModel.mDescipline)
            educationJson.put(BOARD, eduDataModel.mUniverty)
            educationJson.put(GRADE, eduDataModel.mGrade)
            educationJson.put(YOP, eduDataModel.yop)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return educationJson.toString()
    }

    fun isValidateCourse(): Boolean {
        return !eduDataModel.mDescipline.isNullOrBlank()
    }

    fun isValidateGrade(): Boolean {
        return !eduDataModel.mGrade.isNullOrBlank()
    }

    fun isYop(): Boolean {
        return !eduDataModel.yop.isNullOrBlank()
    }

    fun isValidUniversity(): Boolean {
        return !eduDataModel.mUniverty.isNullOrBlank()
    }

    fun isAllDataFieldOkay(): Boolean {
        return isValidateCourse() && isValidateGrade() && isYop() && isValidUniversity()
    }

}
