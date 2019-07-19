package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.Interactor
import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.dataobjects.ProfExperienceDataObject
import com.cvapp.assignment.models.CloudRepositoryModel
import com.cvapp.assignment.models.UploadProfileModelInteractor

/**
 * Created by Mathavan_K on 7/10/2019.
 */

class UploadProfilePresenter(val view: UploadProfileContract.Views) : UploadProfileContract.Presenter, Interactor.Listerner {


    lateinit var experienceDo: ProfExperienceDataObject;
    lateinit var up: UploadProfileModelInteractor
    lateinit var interatorModal: Interactor.Presenter

    override fun onExperienceSave(organization: String, role: String, responsibility: String, duration: String) {
        experienceDo = ProfExperienceDataObject(organization, role, responsibility, duration);
        interatorModal = UploadProfileModelInteractor(experienceDo, this, CloudRepositoryModel())
        interatorModal.saveExperienceData(experienceDo)
    }

    override fun onUploadProfile(path: String) {
        view.showProgressDialog(0.0)
        interatorModal.uploadProfile(path)

    }

    override fun onSuccess(experienceData: String) {
        view.savePersonalData(experienceData)
    }

    override fun onFailure() {
        view.showError()
    }

    override fun onFinishUpload() {
        view.hideProgressDialog()
        view.showUploadProfileSuccess()
        view.clearAllData()
    }

    override fun onFailureUpload() {
        view.hideProgressDialog()
        view.showUploadProfileFailure()
    }


}
