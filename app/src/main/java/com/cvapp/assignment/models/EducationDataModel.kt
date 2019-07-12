package com.cvapp.assignment.models

/**
 * Created by Mathavan_K on 7/11/2019.
 */
/**
 * Education details data model with setter and getter
 */
class EducationDataModel {
    private var mUniverty: String? = ""
    private var mDescipline: String? = ""
    private var mGrade: String? = ""
    var yop: String? = ""

    fun getmUniverty(): String? {
        return mUniverty
    }

    fun setmUniverty(mUniverty: String) {
        this.mUniverty = mUniverty
    }

    fun getmDescipline(): String? {
        return mDescipline
    }

    fun setmDescipline(mDescipline: String) {
        this.mDescipline = mDescipline
    }

    fun getmGrade(): String? {
        return mGrade
    }

    fun setGrade(mGrade: String) {
        this.mGrade = mGrade
    }
}
