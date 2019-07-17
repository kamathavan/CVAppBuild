package com.cvapp.assignment

import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.models.CloudStorageRepository
import com.cvapp.assignment.dataobjects.ProfExperienceDataObject
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
import org.mockito.Mockito.verify

/**
 * Created by Mathavan_K on 7/11/2019.
 */

class UploadFilePresenterTest {

    @Mock
    lateinit var vw: UploadProfileContract.Views

    @Mock
    lateinit var model: CloudStorageRepository


    lateinit var uploadFilePresenter: UploadFilePresenter

    @Mock
    lateinit var model1: UploadProfileContract.Models


    lateinit var profExperienceDataObject: ProfExperienceDataObject


    @Captor
    lateinit var uploadFileListern: ArgumentCaptor<UploadProfileContract.Models.OnListener>

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        profExperienceDataObject = ProfExperienceDataObject("", "", "", "")
        uploadFilePresenter = UploadFilePresenter(vw, model, profExperienceDataObject)
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
        profExperienceDataObject.responsibility = "Team Lead"
        profExperienceDataObject.role = "Team Lead"
        profExperienceDataObject.duration = "2012-2014"
        profExperienceDataObject.organization ="Infosys Limited"
        Assert.assertEquals(true, uploadFilePresenter.isValidateInputField())
    }

    @Test
    fun failureInputFieldTest() {
        profExperienceDataObject.responsibility = ""
        profExperienceDataObject.role = ""
        profExperienceDataObject.duration = "2012-2014"
        profExperienceDataObject.organization ="Infosys Limited"
        Assert.assertEquals(false, uploadFilePresenter.isValidateInputField())
    }

}
