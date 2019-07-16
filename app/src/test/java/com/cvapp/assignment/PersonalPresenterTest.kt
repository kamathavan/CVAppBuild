package com.cvapp.assignment

import android.view.View
import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.PersonalDetailModel
import com.cvapp.assignment.presenter.EducationPresenter
import com.cvapp.assignment.presenter.PersonalPresenter
import junit.framework.Assert.assertEquals

import junit.framework.TestCase

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

import org.mockito.Mockito.verify

/**
 * Created by Mathavan_K on 7/14/2019.
 */


class PersonalPresenterTest {

    @Mock
    lateinit var view: PersonalContract.View

    @Mock
    lateinit var educationPresenter: PersonalPresenter


    lateinit var personalDetailModel: PersonalDetailModel

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        personalDetailModel = PersonalDetailModel("","","","","","","")
        educationPresenter = PersonalPresenter(this.view, personalDetailModel)

    }

    @Test
    fun validateInputSuccess() {
        personalDetailModel.firstname = "Mathavan"
        personalDetailModel.lastname = "Kaliyaperumal"
        personalDetailModel.dob = "31-05-1987"
        personalDetailModel.phone = "8220451927"
        personalDetailModel.city = "Chennai"
        personalDetailModel.emailid = "mathucs@gmail.com"
        personalDetailModel.nation = "India"
        educationPresenter.isValidateInputField()
        assertEquals(false, educationPresenter.isValidateInputField()
        )

    }

    @Test
    fun validateInputFailure() {
        personalDetailModel.firstname =""
        personalDetailModel.lastname = "Kaliyaperumal"
        personalDetailModel.dob = "31-05-1987"
        personalDetailModel.phone = ""
        personalDetailModel.city = "Chennai"
        personalDetailModel.emailid = "mathucs@gmail.com"
        personalDetailModel.nation = "India"
        educationPresenter.isValidateInputField()
        assertEquals(false, educationPresenter.isValidateInputField() )
    }

    @Test
    fun savePersonalDataTest() {
        personalDetailModel.firstname ="Mathavan"
        personalDetailModel.lastname = "Kaliyaperumal"
        personalDetailModel.dob = "31-05-1987"
        personalDetailModel.phone = "xxxxxx"
        personalDetailModel.city = "Chennai"
        personalDetailModel.nation = "India"
        personalDetailModel.emailid = "mathucs@gmail.com"
        educationPresenter.isAllFieldOkay()
        assertEquals(true, educationPresenter.isValidateInputField() )
    }

    @Test
    fun firstLastNameSuccessTest() {
        personalDetailModel.firstname ="Mathavan"
        personalDetailModel.lastname = "Kaliyaperumal"
        TestCase.assertEquals(true, educationPresenter.isValidFirstAndLastName())
    }

    @Test
    fun firstLastNameFailureTest() {
        personalDetailModel.firstname =""
        personalDetailModel.lastname = "Kaliyaperumal"
        assertEquals(false, educationPresenter.isValidFirstAndLastName())
    }

    @Test
    fun successDobTest() {
        personalDetailModel.dob = "31-05-1987"
        assertEquals(true, educationPresenter.isValidDob())
    }

    @Test
    fun failureDobTest() {
        personalDetailModel.dob = ""
        assertEquals(false, educationPresenter.isValidDob())
    }


    @Test
    fun failureCityAndNationTest() {
        personalDetailModel.city = "Chennai"
        personalDetailModel.nation = ""
        assertEquals(false, educationPresenter.isValidCityAndNation())
    }

    @Test
    fun successCityAndNationTest() {
        personalDetailModel.city = "Chennai"
        personalDetailModel.nation = "India"
        assertEquals(true, educationPresenter.isValidCityAndNation())

    }

    @Test
    fun successEmailTest() {
        personalDetailModel.emailid = "mathucs@gmail.com"
        assertEquals(true, educationPresenter.isValidEmailId())
    }

    @Test
    fun failureEmailTest() {
        personalDetailModel.emailid = ""
        assertEquals(false, educationPresenter.isValidEmailId())
    }
}
