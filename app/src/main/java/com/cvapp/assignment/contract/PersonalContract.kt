package com.cvapp.assignment.contract

import android.app.Activity

/**
 * Created by Mathavan_K on 7/10/2019.
 */

interface PersonalContract {

    interface View {
        fun savePersonalData( data:String)
        fun showError()
    }

    interface Presenter {
        fun onSaveBtnClick()
    }
}
