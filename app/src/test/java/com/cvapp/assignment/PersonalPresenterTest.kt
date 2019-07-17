package com.cvapp.assignment

import com.cvapp.assignment.contract.ProfileContract
import com.cvapp.assignment.dataobjects.PersonalDataObject
import com.cvapp.assignment.presenter.PersonalPresenter
import junit.framework.Assert.assertEquals

import junit.framework.TestCase

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by Mathavan_K on 7/14/2019.
 */


class PersonalPresenterTest {

    @Mock
    lateinit var view: ProfileContract.View


    lateinit var educationPresenter: PersonalPresenter


    lateinit var personalDataObject: PersonalDataObject

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        personalDataObject = PersonalDataObject("", "", "", "", "", "", "")
        educationPresenter = PersonalPresenter(this.view, personalDataObject)

    }

    @Test
    fun validateInputSuccess() {
        personalDataObject.firstname = "Mathavan"
        personalDataObject.lastname = "Kaliyaperumal"
        personalDataObject.dob = "31-05-1987"
        personalDataObject.phone = "8220451927"
        personalDataObject.city = "Chennai"
        personalDataObject.emailid = "mathucs@gmail.com"
        personalDataObject.nation = "India"
        educationPresenter.isValidateInputField()
        assertEquals(true, educationPresenter.isValidateInputField()
        )

    }

    @Test
    fun validateInputFailure() {
        personalDataObject.firstname =""
        personalDataObject.lastname = "Kaliyaperumal"
        personalDataObject.dob = "31-05-1987"
        personalDataObject.phone = ""
        personalDataObject.city = "Chennai"
        personalDataObject.emailid = "mathucs@gmail.com"
        personalDataObject.nation = "India"
        educationPresenter.isValidateInputField()
        assertEquals(false, educationPresenter.isValidateInputField() )
    }

    @Test
    fun savePersonalDataTest() {
        personalDataObject.firstname ="Mathavan"
        personalDataObject.lastname = "Kaliyaperumal"
        personalDataObject.dob = "31-05-1987"
        personalDataObject.phone = "xxxxxx"
        personalDataObject.city = "Chennai"
        personalDataObject.nation = "India"
        personalDataObject.emailid = "mathucs@gmail.com"
        educationPresenter.isAllFieldOkay()
        assertEquals(true, educationPresenter.isValidateInputField() )
    }

    @Test
    fun firstLastNameSuccessTest() {
        personalDataObject.firstname ="Mathavan"
        personalDataObject.lastname = "Kaliyaperumal"
        TestCase.assertEquals(true, educationPresenter.isValidFirstAndLastName())
    }

    @Test
    fun firstLastNameFailureTest() {
        personalDataObject.firstname =""
        personalDataObject.lastname = "Kaliyaperumal"
        assertEquals(false, educationPresenter.isValidFirstAndLastName())
    }

    @Test
    fun successDobTest() {
        personalDataObject.dob = "31-05-1987"
        assertEquals(true, educationPresenter.isValidDob())
    }

    @Test
    fun failureDobTest() {
        personalDataObject.dob = ""
        assertEquals(false, educationPresenter.isValidDob())
    }


    @Test
    fun failureCityAndNationTest() {
        personalDataObject.city = "Chennai"
        personalDataObject.nation = ""
        assertEquals(false, educationPresenter.isValidCityAndNation())
    }

    @Test
    fun successCityAndNationTest() {
        personalDataObject.city = "Chennai"
        personalDataObject.nation = "India"
        assertEquals(true, educationPresenter.isValidCityAndNation())

    }

    @Test
    fun successEmailTest() {
        personalDataObject.emailid = "mathucs@gmail.com"
        assertEquals(true, educationPresenter.isValidEmailId())
    }

    @Test
    fun failureEmailTest() {
        personalDataObject.emailid = ""
        assertEquals(false, educationPresenter.isValidEmailId())
    }
}
