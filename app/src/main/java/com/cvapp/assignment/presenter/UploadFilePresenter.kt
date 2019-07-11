package com.cvapp.assignment.presenter

import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.CloudStorageRepository

/**
 * Created by Mathavan_K on 7/8/2019.
 */

class UploadFilePresenter(private val vw: UploadProfileContract.Views, model: CloudStorageRepository) : UploadProfileContract.ClickListner, UploadProfileContract.Models.OnListener {

    private val model: UploadProfileContract.Models


    init {
        this.model = model
    }

    /**
     * stor profile to cloud storage on click save button
     */
    override fun onButtonClick(path: String) {
        if (vw.isEnabled(true)) {
            vw.showProgressDialog(0.0)
            model.uploadProfile(path, this)
        } else {
            vw.showFailureMsg()
        }
    }

    /**
     * move to personal details screen
     */
    override fun onPersonBtnClick() {
        vw.navigateToPersonal()
    }

    /**
     * move to education details
     */
    override fun onEducationBtnClick() {
        vw.navigateToEducation()
    }

    /**
     * move to experience details
     */
    override fun onExperTechBtnClick() {
        vw.navigateToExperience()
    }

    /**
     * on finished to success screen
     */
    override fun onFinished() {
        vw.hideProgressDialog()
        vw.showsuccessMsg()
        vw.clearAllData()
    }

    /**
     *  failure when profile submitting
     */
    override fun onFailure(t: Throwable) {
        vw.hideProgressDialog()
        vw.showFailureMsg()
    }

    /**
     * when submitting the profile - on progress
     */
    override fun onProgress(progress: Double) {
        vw.showProgressDialog(progress)
    }
}
