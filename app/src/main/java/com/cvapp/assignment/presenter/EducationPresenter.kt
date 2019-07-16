package com.cvapp.assignment.presenter

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

    /**
     *  add the educations details to profile
     */
    override fun onSaveBtnClick() {
        val eduData = makeEducationJson(eduDataModel)
        view.savePersonalData(eduData)
    }

    fun makeEducationJson(eduDataModel: EducationDataModel): String {
        val educationJson = JSONObject()
        try {
            educationJson.put(GRADUATE, eduDataModel.course)
            educationJson.put(BOARD, eduDataModel.univerty)
            educationJson.put(GRADE, eduDataModel.grade)
            educationJson.put(YOP, eduDataModel.yop)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return educationJson.toString()
    }

    fun isValidateCourse(): Boolean {
        return !eduDataModel.course.isNullOrBlank()
    }

    fun isValidateGrade(): Boolean {
        return !eduDataModel.grade.isNullOrBlank()
    }

    fun isYop(): Boolean {
        return !eduDataModel.yop.isNullOrBlank()
    }

    fun isValidUniversity(): Boolean {
        return !eduDataModel.univerty.isNullOrBlank()
    }

    fun isAllDataFieldOkay(): Boolean {
        return isValidateCourse() && isValidateGrade() && isYop() && isValidUniversity()
    }

    override fun isValidateInputField(): Boolean {
        if (isAllDataFieldOkay()) {
            return true
        } else {
            view.showError()
            return false
        }
    }

}
