package com.cvapp.assignment.models

import com.cvapp.assignment.dataobjects.EducationDataObject
import com.google.gson.Gson

/**
 * Created by Mathavan_K on 7/17/2019.
 */
class EducationModelInteractor {
    lateinit var educationInfo: String

    interface onListener {
        fun onSuccess(person: String)
        fun onfailure()
    }

    fun saveEducationInfo(educationDO: EducationDataObject, onListener: onListener) {
        if (!educationDO.univerty.isNullOrBlank()
                && !educationDO.yop.isNullOrBlank()
                && !educationDO.course.isNullOrBlank()
                && !educationDO.grade.isNullOrBlank()
        ) {
            val gsonObj = Gson();
            educationInfo = gsonObj.toJson(educationDO).toString();
            onListener.onSuccess(educationInfo)
        } else {
            onListener.onfailure()
        }
    }
}