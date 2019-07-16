package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.ExperienceDataModel
import com.cvapp.assignment.utils.Constants.Companion.CORESKILL
import com.cvapp.assignment.utils.Constants.Companion.OTHERSKILL
import com.cvapp.assignment.utils.Constants.Companion.PROFES_SUMM
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class TechExperiencePresenter(private val view: PersonalContract.View, private val experDataModel: ExperienceDataModel) : PersonalContract.Presenter {


    /**
     * add the technical experience into the profile
     */
    override fun onSaveBtnClick() {
        val techSkill = makeTechSkillJson(experDataModel)
        view.savePersonalData(techSkill)
    }

    fun makeTechSkillJson(techSkillData: ExperienceDataModel): String {
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

    fun isValidCoreSkill() : Boolean{
        return ! experDataModel.coreSkill.isNullOrEmpty()
    }

    fun isValidExperience():Boolean {
        return !experDataModel.otherSkill.isNullOrEmpty()
    }

    fun isValidProfessionSummary():Boolean{
        return !experDataModel.profSummary.isNullOrEmpty()
    }

    fun isAllFieldOkay():Boolean {
        return isValidCoreSkill()&&isValidExperience()&&isValidProfessionSummary()
    }
    override fun isValidateInputField(): Boolean {
        if(isAllFieldOkay()){
            return true
        }else{
            view.showError()
            return false;
        }
    }
}
