package com.cvapp.assignment.presenter

import android.support.design.widget.TextInputEditText

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.PersonalDetailModel

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class PersonalPresenter(private val view: PersonalContract.View, private val perDataModel: PersonalDetailModel) : PersonalContract.Presenter {
    /**
     * add the personal details
     */
    override fun onSaveBtnClick() {
        if (!perDataModel.firstname.isEmpty() && !perDataModel.lastname.isEmpty() &&
                !perDataModel.city.isEmpty() && !perDataModel.dob.isEmpty() &&
                !perDataModel.nation.isEmpty() && !perDataModel.phone.isEmpty()
                && !perDataModel.emailid.isEmpty()) {
            view.savePersonalData()
        } else {
            view.showError()
        }

    }
}
