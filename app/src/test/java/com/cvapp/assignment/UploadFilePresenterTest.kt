package com.cvapp.assignment

import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.CloudStorageRepository
import com.cvapp.assignment.models.ExperienceDataModel
import com.cvapp.assignment.models.ProjExperDataModel
import com.cvapp.assignment.presenter.UploadFilePresenter

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

import org.mockito.Matchers.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * Created by Mathavan_K on 7/11/2019.
 */

class UploadFilePresenterTest {

    @Mock
    lateinit var vw: UploadProfileContract.Views

    @Mock
    lateinit var model: CloudStorageRepository

    @Mock
    lateinit var uploadFilePresenter: UploadFilePresenter

    @Mock
    lateinit var model1: UploadProfileContract.Models


    lateinit var projExperDataModel: ProjExperDataModel


    @Captor
    lateinit var uploadFileListern: ArgumentCaptor<UploadProfileContract.Models.OnListener>

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        projExperDataModel = ProjExperDataModel()
        uploadFilePresenter = UploadFilePresenter(vw, model, projExperDataModel)
    }

    @Test
    fun onSaveProfileTest() {
        val path = "/data/user/0/com.cvapp.assignment/files/profile.json"
        uploadFilePresenter.onUploadProfile(path)
        verify<UploadProfileContract.Views>(vw).showProgressDialog(eq(0.0))
        Mockito.verify<CloudStorageRepository>(model).uploadProfile(eq(path), uploadFileListern.capture())
        uploadFileListern.value.onFailure(Throwable("Server may down."))
        uploadFileListern.value.onFinished()

    }

    @Test
    fun successInputFieldTest() {
        projExperDataModel.responsibility = "Team Lead"
        projExperDataModel.role = "Team Lead"
        projExperDataModel.duration = "2012-2014"
        projExperDataModel.organization ="Infosys Limited"
        Assert.assertEquals(true, uploadFilePresenter.isValidateInputField())
    }

    @Test
    fun failureInputFieldTest() {
        projExperDataModel.responsibility = ""
        projExperDataModel.role = ""
        projExperDataModel.duration = "2012-2014"
        projExperDataModel.organization ="Infosys Limited"
        Assert.assertEquals(false, uploadFilePresenter.isValidateInputField())
    }

}
