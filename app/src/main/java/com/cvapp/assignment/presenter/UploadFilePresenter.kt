package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.CloudStorageRepository
import com.cvapp.assignment.models.ProjExperDataModel
import com.cvapp.assignment.utils.Constants.Companion.DURATIONS
import com.cvapp.assignment.utils.Constants.Companion.ORGANIZATION
import com.cvapp.assignment.utils.Constants.Companion.RESPONSIBILITY
import com.cvapp.assignment.utils.Constants.Companion.ROLE

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Mathavan_K on 7/8/2019.
 */

class UploadFilePresenter(private val vw: UploadProfileContract.Views, repmodel: CloudStorageRepository, private val prDataModel: ProjExperDataModel) : UploadProfileContract.ClickListner, UploadProfileContract.Models.OnListener {

    private val repositoryModel: UploadProfileContract.Models

    init {
        this.repositoryModel = repmodel
    }

    /**
     * upload the profile to cloud repository
     */
    override fun onUploadProfile(path: String) {
        vw.showProgressDialog(0.0);
        repositoryModel.uploadProfile(path, this);
    }

    /**
     * validate the all  inputfield
     */
    override fun isValidateInputField(): Boolean {
        if (!prDataModel.organization.isNullOrEmpty() && !prDataModel.duration.isNullOrEmpty() &&
                !prDataModel.role.isNullOrEmpty() && !prDataModel.responsibility.isNullOrEmpty()) {
            return true
        } else {
            vw.showValidationError()
            return false;
        }
    }

    /**
     *  collect the all data and store into the local storage
     */
    override fun onSaveButtonClick() {
        val projExpData = makeProfile()
        vw.saveProfile(projExpData)
    }

    /**
     * check the valid organization
     */
    fun isValidOrganization(): Boolean {
        return !prDataModel.organization.isNullOrEmpty()
    }

    /**
     * check the valid role
     */
    fun isValidRole(): Boolean {
        return !prDataModel.role.isNullOrEmpty();
    }

    /**
     * check the validresponsibility
     */

    fun isValiResponsibility(): Boolean {
        return !prDataModel.responsibility.isNullOrEmpty();
    }

    /**
     * check the valid duration
     */
    fun isValidDuration(): Boolean {
        return !prDataModel.duration.isNullOrBlank()
    }

    fun isAllFieldOkay(): Boolean {
        return isValidOrganization() && isValidDuration() && isValidRole() && isValiResponsibility()
    }

    /**
     *  after success profile uploaded call this method
     */
    override fun onFinished() {
        vw.hideProgressDialog()
        vw.showsuccessMsg()
        vw.clearAllData()
    }

    /**
     * after failure to upload profile
     */
    override fun onFailure(t: Throwable) {
        vw.hideProgressDialog()
        vw.showFailureMsg()
    }

    override fun onProgress(progress: Double) {
    }

    /**
     * make the json for professional experience info
     */

    fun makeProfile(): String {
        val jsonObject = JSONObject()
        try {
            jsonObject.put(ORGANIZATION, prDataModel.organization)
            jsonObject.put(DURATIONS, prDataModel.duration)
            jsonObject.put(ROLE, prDataModel.role)
            jsonObject.put(RESPONSIBILITY, prDataModel.responsibility)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsonObject.toString()
    }


}
