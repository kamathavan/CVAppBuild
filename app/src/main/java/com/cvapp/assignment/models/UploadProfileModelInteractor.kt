package com.cvapp.assignment.models

import android.util.Log
import com.cvapp.assignment.contract.Interactor
import com.cvapp.assignment.dataobjects.ProfExperienceDataObject
import com.google.gson.Gson

/**
 * Created by Mathavan_K on 7/17/2019.
 */
class UploadProfileModelInteractor(var dataModel: ProfExperienceDataObject,
                                   val listerner: Interactor.Listerner,
                                   val model: CloudRepositoryModel) : Interactor.Presenter,Interactor.Models.OnListener {


    lateinit var experienceData: String
    lateinit var repoModel: CloudRepositoryModel
    lateinit var interactorListerer: Interactor.Listerner
    init {
        this.repoModel = model;
        this.interactorListerer = listerner
    }

    override fun saveExperienceData(experienceDataObject: ProfExperienceDataObject) {
        if (!experienceDataObject.organization.isNullOrBlank()
                && !experienceDataObject.role.isNullOrBlank()
                && !experienceDataObject.responsibility.isNullOrBlank()
                && !experienceDataObject.duration.isNullOrBlank()
        ) {
            val gsonObj = Gson();
            experienceData = gsonObj.toJson(experienceDataObject).toString();
            interactorListerer.onSuccess(experienceData)
        } else {
            listerner.onFailure()
        }
    }

    override fun uploadProfile(path: String) {
        repoModel.uploadProfile(path, this)
    }

    override fun onFinished() {
        interactorListerer.onFinishUpload();
    }

    override fun onFailure(t: Throwable) {
        interactorListerer.onFailureUpload();
    }

    override fun onProgress(percentage: Double) {
        Log.v("","on progress-- uploading file")
    }


}