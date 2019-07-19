package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.ProfileView
import com.cvapp.assignment.dataobjects.TechSkillDataObject
import com.cvapp.assignment.models.TechSkillModelInteractor

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class TechExperienceInfoPresenter(val view: ProfileView) : TechSkillModelInteractor.onListener {

    lateinit var techSkillDataObject: TechSkillDataObject

    fun saveTechSkillInfo(coreSkill: String, experience: String, profSummary: String) {
        techSkillDataObject = TechSkillDataObject(coreSkill, experience, profSummary)
        TechSkillModelInteractor().saveTechSkill(techSkillDataObject, this)
    }

    override fun onSuccess(person: String) {
        view.savePersonalData(person)
    }

    override fun onfailure() {
        view.showError()
    }



}
