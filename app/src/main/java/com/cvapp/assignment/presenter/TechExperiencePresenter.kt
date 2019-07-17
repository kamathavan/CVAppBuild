package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.ProfileContract
import com.cvapp.assignment.dataobjects.TechSkillDataObject
import com.cvapp.assignment.utils.Constants.Companion.CORESKILL
import com.cvapp.assignment.utils.Constants.Companion.OTHERSKILL
import com.cvapp.assignment.utils.Constants.Companion.PROFES_SUMM
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class TechExperiencePresenter(private val view: ProfileContract.View, private val experDataObject: TechSkillDataObject) : ProfileContract.Presenter {


    /**
     * add the technical experience into the profile
     */
    override fun onSaveBtnClick() {
        val techSkill = makeTechSkillJson(experDataObject)
        view.savePersonalData(techSkill)
    }

    /**
     * validate all input field
     */
    override fun isValidateInputField(): Boolean {
        if (isAllFieldOkay()) {
            return true
        } else {
            view.showError()
            return false;
        }
    }

    /**
     * make the consolidated json
     */
    fun makeTechSkillJson(techSkillData: TechSkillDataObject): String {
        val jsonTechJson = JSONObject()
        try {
            jsonTechJson.put(CORESKILL, techSkillData.coreSkill)
            jsonTechJson.put(OTHERSKILL, techSkillData.otherSkill)
            jsonTechJson.put(PROFES_SUMM, techSkillData.profSummary)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsonTechJson.toString()
    }

    /**
     * check core skill whether empty or null
     */
    fun isValidCoreSkill(): Boolean {
        return !experDataObject.coreSkill.isNullOrEmpty()
    }
    /**
     * check experience skill whether empty or null
     */
    fun isValidExperience(): Boolean {
        return !experDataObject.otherSkill.isNullOrEmpty()
    }
    /**
     * check profession skill whether empty or null
     */
    fun isValidProfessionSummary(): Boolean {
        return !experDataObject.profSummary.isNullOrEmpty()
    }
    /**
     * check all skills
     */
    fun isAllFieldOkay(): Boolean {
        return isValidCoreSkill() && isValidExperience() && isValidProfessionSummary()
    }

}
