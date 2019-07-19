package com.cvapp.assignment.models

import com.cvapp.assignment.dataobjects.TechSkillDataObject
import com.google.gson.Gson

/**
 * Created by Mathavan_K on 7/17/2019.
 */
class TechSkillModelInteractor {
    lateinit var techSkill: String

    interface onListener {
        fun onSuccess(person: String)
        fun onfailure()
    }

    fun saveTechSkill(techSkillDo: TechSkillDataObject, onListener: onListener) {
        if (!techSkillDo.otherSkill.isNullOrBlank()
                && !techSkillDo.coreSkill.isNullOrBlank()
                && !techSkillDo.profSummary.isNullOrBlank()

        ) {
            val gsonObj = Gson();
            techSkill = gsonObj.toJson(techSkillDo).toString();
            onListener.onSuccess(techSkill)
        } else {
            onListener.onfailure()
        }
    }
}