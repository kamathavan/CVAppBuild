package com.cvapp.assignment

import android.view.View
import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.PersonalDetailModel
import com.cvapp.assignment.presenter.EducationPresenter
import com.cvapp.assignment.presenter.PersonalPresenter

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

@RunWith(MockitoJUnitRunner::class)
class PersonalPresenterTest : TestCase() {

    @Mock
    private val view: PersonalContract.View? = null

    @Mock
    private var educationPresenter: PersonalPresenter? = null


    private var personalDetailModel: PersonalDetailModel? = null

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        personalDetailModel = PersonalDetailModel()
        educationPresenter = PersonalPresenter(this!!.view!!, personalDetailModel!!)

    }

    @Test
    fun validateInputSuccess() {
        personalDetailModel!!.firstname = "Mathavan"
        personalDetailModel!!.lastname = "Kaliyaperumal"
        personalDetailModel!!.dob = "31-05-1987"
        personalDetailModel!!.phone = "8220451927"
        personalDetailModel!!.city = "Chennai"
        personalDetailModel!!.emailid = "mathucs@gmail.com"
        personalDetailModel!!.nation = "India"
        educationPresenter!!.isValidateInputField()

    }

    @Test
    fun validateInputFailure() {
        personalDetailModel!!.firstname =""
        personalDetailModel!!.lastname = "Kaliyaperumal"
        personalDetailModel!!.dob = "31-05-1987"
        personalDetailModel!!.phone = ""
        personalDetailModel!!.city = "Chennai"
        personalDetailModel!!.emailid = "mathucs@gmail.com"
        personalDetailModel!!.nation = "India"
        educationPresenter!!.isValidateInputField()
        verify(view)!!.showError()

    }

    @Test
    fun savePersonalDataTest() {
        personalDetailModel!!.firstname ="Mathavan"
        personalDetailModel!!.lastname = "Kaliyaperumal"
        personalDetailModel!!.dob = "31-05-1987"
        personalDetailModel!!.phone = "xxxxxx"
        personalDetailModel!!.city = "Chennai"
        personalDetailModel!!.nation = "India"
        personalDetailModel!!.emailid = "mathucs@gmail.com"
        educationPresenter!!.isAllFieldOkay()
    }

    @Test
    fun firstLastNameTest() {
        personalDetailModel!!.firstname ="Mathavan"
        personalDetailModel!!.lastname = "Kaliyaperumal"
        educationPresenter!!.isValidFirstAndLastName()
    }

    @Test
    fun firstLastNameTestFailure() {
        personalDetailModel!!.firstname =""
        personalDetailModel!!.lastname = "Kaliyaperumal"
        educationPresenter!!.isValidFirstAndLastName()
    }

    @Test
    fun successDobTest() {
        personalDetailModel!!.dob = "31-05-1987"
        educationPresenter!!.isValidDob()
    }

    @Test
    fun failureDobTest() {
        personalDetailModel!!.dob = ""
        educationPresenter!!.isValidDob()
    }


    @Test
    fun failureCityAndNationTest() {
        personalDetailModel!!.city = "Chennai"
        personalDetailModel!!.nation = ""
        educationPresenter!!.isValidCityAndNation()
    }

    @Test
    fun successCityAndNationTest() {
        personalDetailModel!!.city = "Chennai"
        personalDetailModel!!.nation = "India"
        educationPresenter!!.isValidCityAndNation()

    }

    @Test
    fun successEmailTest() {
        personalDetailModel!!.emailid = "mathucs@gmail.com"
        educationPresenter!!.isValidEmailId()
    }

    @Test
    fun failureEmailTest() {
        personalDetailModel!!.emailid = ""
        educationPresenter!!.isValidEmailId()
    }
}
