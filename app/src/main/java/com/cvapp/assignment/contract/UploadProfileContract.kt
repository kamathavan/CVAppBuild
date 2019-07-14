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
        fun showValidationError()
        fun clearAllData()
        fun saveProfile(profile: String)

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
        fun onUploadProfile(path: String)
        fun onSaveButtonClick();
    }

}
