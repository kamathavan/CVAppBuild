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

    /**
     *  add the educations details to profile
     */
    override fun onSaveBtnClick() {
        if (!eduDataModel.mDescipline!!.isEmpty() && !eduDataModel.mGrade!!.isEmpty() &&
                !eduDataModel.yop!!.isEmpty() && !eduDataModel.mUniverty!!.isEmpty()) {
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

}
