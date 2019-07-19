package com.cvapp.assignment.Presenter

import com.cvapp.assignment.contract.UploadProfileContract
import com.cvapp.assignment.dataobjects.ProfExperienceDataObject
import com.cvapp.assignment.presenter.UploadProfilePresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Mathavan_K on 7/18/2019.
 */

class ExperiencePresenterTest{

    @Mock
    lateinit var view: UploadProfileContract.Views


    lateinit var uploadProfilePresenter: UploadProfilePresenter

    lateinit var experieneDo: ProfExperienceDataObject

    lateinit var  experienceData :String;

    @Before
    fun setUpProsenter(){
        MockitoAnnotations.initMocks(this)
        uploadProfilePresenter  = UploadProfilePresenter(view)


    }

    @Test
    fun  onExperienceSave(){
        uploadProfilePresenter.onExperienceSave("Infosys Limited",
                "Tech Analysist",
                "Developer",
                "Jan2003-June2012")
    }

    @Test
    fun  onFailureTest(){
        uploadProfilePresenter.onFailure()
        verify(view).showError()
    }
    @Test
    fun  onSuccessTest(){
        experieneDo =
                ProfExperienceDataObject(
                        "Infosys Limited",
                        "Tech Analysist",
                        "Developer" ,
                        "Jan2003-June2012"
                )
        val gson = Gson()
        experienceData = gson.toJson(experieneDo)
        uploadProfilePresenter.onSuccess(experienceData)
        verify(view).savePersonalData(experienceData)
    }

}