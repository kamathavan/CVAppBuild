package com.cvapp.assignment.presenter

import android.support.design.widget.TextInputEditText

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.ExperienceDataModel

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class TechExperiencePresenter(private val view: PersonalContract.View, private val experDataModel: ExperienceDataModel) : PersonalContract.Presenter {
    /**
     * add the technical experience into the profile
     */
    override fun onSaveBtnClick() {
        if (!experDataModel.coreSkill.isEmpty() && !experDataModel.otherSkill.isEmpty()
                && !experDataModel.organization.isEmpty() && !experDataModel.durations.isEmpty()
                && !experDataModel.projectsDetails.isEmpty() && !experDataModel.role.isEmpty()) {
            view.savePersonalData()
        } else {
            view.showError()
        }
    }
}
