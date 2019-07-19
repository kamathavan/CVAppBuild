package com.cvapp.assignment.contract

import android.app.Activity
import com.cvapp.assignment.dataobjects.ProfExperienceDataObject

/**
 * Created by Mathavan_K on 7/10/2019.
 */

interface Interactor {

    interface Listerner {
        fun onSuccess( data:String)
        fun onFailure()
        fun onFinishUpload();
        fun onFailureUpload();
    }
    interface Models {
        interface OnListener {
            fun onFinished()
            fun onFailure(t: Throwable)
            fun onProgress(percentage: Double)
        }
        fun uploadProfile(filePath: String, onListener: OnListener)
    }
    interface Presenter {
        fun saveExperienceData(experienceDataObject: ProfExperienceDataObject)
        fun uploadProfile(path:String)
    }

}
