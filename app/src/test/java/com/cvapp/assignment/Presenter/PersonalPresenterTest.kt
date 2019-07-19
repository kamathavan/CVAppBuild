package com.cvapp.assignment.Presenter

import com.cvapp.assignment.contract.ProfileView
import com.cvapp.assignment.dataobjects.PersonalDataObject
import com.cvapp.assignment.presenter.PersonalInfoPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Mathavan_K on 7/18/2019.
 */

class PersonalPresenterTest{

    @Mock
    lateinit var view: ProfileView


    lateinit var educationInfoPresenter: PersonalInfoPresenter


    lateinit var personalDataObject: PersonalDataObject

    lateinit var  personalData :String;

    @Before
    fun setUpProsenter(){
        MockitoAnnotations.initMocks(this)
        educationInfoPresenter  = PersonalInfoPresenter(view)


    }

    @Test
    fun  onNextTest(){
        educationInfoPresenter.onSavePersonalInfo("Mathavan",
                "Kaliyaperumal",
                "31-05-1987",
                "mathucs@gmail.com",
                "chennai",
                "8220251927",
                "india")
    }

    @Test
    fun  onFailureTest(){
        educationInfoPresenter.onfailure()
        verify(view).showError()
    }
    @Test
    fun  onSuccessTest(){
        personalDataObject =
                PersonalDataObject(
                        "Mathavan",
                        "La",
                        "8222" ,
                        "21-09-1987",
                        "chennai",
                        "ema",
                        "India"
                )
        val gson = Gson()
        personalData = gson.toJson(personalDataObject)
        educationInfoPresenter.onSuccess(personalData)
        verify(view).savePersonalData(personalData)
    }

}