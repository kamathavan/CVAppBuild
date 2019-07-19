package com.cvapp.assignment.contract

/**
 * Created by Mathavan_K on 7/8/2019.
 */

interface UploadProfileContract {

    interface Views {
        fun showProgressDialog(progress: Double)
        fun hideProgressDialog()
        fun showUploadProfileSuccess()
        fun showUploadProfileFailure()
        fun showError()
        fun clearAllData()
        fun savePersonalData(profile: String)
    }

    interface Presenter {
        fun onExperienceSave(organization:String,role:String,responsibility:String,duration:String);
        fun onUploadProfile(path: String)
    }

}
