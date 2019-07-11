package com.cvapp.assignment.contract

/**
 * Created by Mathavan_K on 7/8/2019.
 */

interface UploadProfileContract {

    interface Views {
        fun showProgressDialog(progress: Double)
        fun hideProgressDialog()
        fun showsuccessMsg()
        fun showFailureMsg()
        fun navigateToPersonal()
        fun navigateToEducation()
        fun navigateToExperience()
        fun isEnabled(enable: Boolean): Boolean
        fun clearAllData()

    }

    interface Models {
        interface OnListener {

            fun onFinished()

            fun onFailure(t: Throwable)

            fun onProgress(percentage: Double)
        }

        fun uploadProfile(filePath: String, onListener: OnListener)
    }

    interface ClickListner {
        fun onButtonClick(path: String)
        fun onPersonBtnClick()
        fun onEducationBtnClick()
        fun onExperTechBtnClick()
    }

}
