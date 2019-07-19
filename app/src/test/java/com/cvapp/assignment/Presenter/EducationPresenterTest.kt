package com.cvapp.assignment.Presenter

import com.cvapp.assignment.contract.ProfileView
import com.cvapp.assignment.dataobjects.EducationDataObject
import com.cvapp.assignment.dataobjects.PersonalDataObject
import com.cvapp.assignment.presenter.EducationInfoPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Mathavan_K on 7/18/2019.
 */

class EducationPresenterTest{

    @Mock
    lateinit var view: ProfileView


    lateinit var educationPresenter: EducationInfoPresenter

    lateinit var educationDo: EducationDataObject

    lateinit var  educationData :String;

    @Before
    fun setUpProsenter(){
        MockitoAnnotations.initMocks(this)
        educationPresenter  = EducationInfoPresenter(view)


    }

    @Test
    fun  onEducationSaveTest(){
        educationPresenter.saveEducation("BE",
                "Anna University",
                "A",
                "2009")
    }

    @Test
    fun  onFailureTest(){
        educationPresenter.onfailure()
        verify(view).showError()
    }
    @Test
    fun  onSuccessTest(){
        educationDo =
                EducationDataObject(
                        "Anna University",
                        "BE",
                        "A" ,
                        "2009"
                )
        val gson = Gson()
        educationData = gson.toJson(educationDo)
        educationPresenter.onSuccess(educationData)
        verify(view).savePersonalData(educationData)
    }

}