package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.CloudStorageRepository
import com.cvapp.assignment.models.ProjExperDataModel

import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Mathavan_K on 7/8/2019.
 */

class UploadFilePresenter(private val vw: UploadProfileContract.Views, repmodel: CloudStorageRepository, private val prDataModel: ProjExperDataModel) : UploadProfileContract.ClickListner, UploadProfileContract.Models.OnListener {

    private val model: UploadProfileContract.Models

    init {
        this.model = repmodel
    }

    override fun onUploadProfile(path: String) {
        if (!prDataModel.organization.isEmpty()) {
            vw.showProgressDialog(0.0);
            model.uploadProfile(path, this);
        } else {
            vw.showValidationError();
        }
    }


    override fun onFinished() {
        vw.hideProgressDialog()
        vw.showsuccessMsg()
        vw.clearAllData()
        //vw.navigateToHome()
    }

    override fun onFailure(t: Throwable) {
        vw.hideProgressDialog()
        vw.showFailureMsg()
        //vw.navigateToHome()
    }

    override fun onProgress(progress: Double) {
        //vw.showProgressDialog(progress)
    }

    override fun onSaveButtonClick() {
        val projExpData = makeProfile()
        vw.saveProfile(projExpData)
    }

    fun makeProfile(): String {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("Organization", prDataModel.organization)
            jsonObject.put("Duration", prDataModel.duration)
            jsonObject.put("Role", prDataModel.role)
            jsonObject.put("Responsibility", prDataModel.responsibility)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsonObject.toString()
    }
}
