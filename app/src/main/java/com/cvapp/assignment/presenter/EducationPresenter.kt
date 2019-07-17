package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.ProfileContract
import com.cvapp.assignment.dataobjects.EducationDataObject
import com.cvapp.assignment.utils.Constants.Companion.BOARD
import com.cvapp.assignment.utils.Constants.Companion.GRADE
import com.cvapp.assignment.utils.Constants.Companion.GRADUATE
import com.cvapp.assignment.utils.Constants.Companion.YOP
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class EducationPresenter(private val view: ProfileContract.View, private val eduDataObject: EducationDataObject) : ProfileContract.Presenter {

    /**
     *  add the educations details to profile
     */
    override fun onSaveBtnClick() {
        val eduData = makeEducationJson(eduDataObject)
        view.savePersonalData(eduData)
    }

    override fun isValidateInputField(): Boolean {
        if (isAllDataFieldOkay()) {
            return true
        } else {
            return false
            view.showError()
        }
    }

    private fun makeEducationJson(eduDataObject: EducationDataObject): String {
        val educationJson = JSONObject()
        try {
            educationJson.put(GRADUATE, eduDataObject.course)
            educationJson.put(BOARD, eduDataObject.univerty)
            educationJson.put(GRADE, eduDataObject.grade)
            educationJson.put(YOP, eduDataObject.yop)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return educationJson.toString()
    }

    fun isValidateCourse(): Boolean {
        return !eduDataObject.course.isNullOrBlank()
    }

    private fun isValidateGrade(): Boolean {
        return !eduDataObject.grade.isNullOrBlank()
    }

    fun isYop(): Boolean {
        return !eduDataObject.yop.isNullOrBlank()
    }

    fun isValidUniversity(): Boolean {
        return !eduDataObject.univerty.isNullOrBlank()
    }

    private fun isAllDataFieldOkay(): Boolean {
        return isValidateCourse() && isValidateGrade() && isYop() && isValidUniversity()
    }


}
