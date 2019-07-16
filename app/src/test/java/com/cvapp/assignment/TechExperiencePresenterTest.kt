package com.cvapp.assignment

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.ExperienceDataModel
import com.cvapp.assignment.presenter.TechExperiencePresenter

import junit.framework.TestCase

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

import org.mockito.Mockito.verify

/**
 * Created by Mathavan_K on 7/15/2019.
 */

@RunWith(MockitoJUnitRunner::class)
class TechExperiencePresenterTest : TestCase() {

    var dataModel: ExperienceDataModel ? = null

    @Mock
    var view: PersonalContract.View? = null

    @Mock
    var presenter: TechExperiencePresenter?=null

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        dataModel = ExperienceDataModel()
        presenter = TechExperiencePresenter(this!!.view!!, dataModel!!)
    }

    @Test
    fun isInputFieldSuccess() {
        dataModel!!.txtProfSummary = "I am android developer"
        dataModel!!.otherSkill = "9"
        dataModel!!.coreSkill = "Android"
        TestCase.assertEquals(true, presenter!!.isValidateInputField())
    }

    @Test
    fun isInputFieldFailure() {
        dataModel!!.txtProfSummary = "I am android developer"
        dataModel!!.otherSkill = ""
        dataModel!!.coreSkill = ""
        TestCase.assertEquals(false, presenter!!.isValidateInputField())
        verify(view)!!.showError()
    }

    @Test
    fun successCoreSkill() {
        dataModel!!.coreSkill = "Android"
        TestCase.assertEquals(true, presenter!!.isValidCoreSkill())
        verify(view)!!.showError()
    }

    @Test
    fun failureCoreSkill() {
        dataModel!!.coreSkill = ""
        TestCase.assertEquals(false, presenter!!.isValidCoreSkill())
        verify(view)!!.showError()
    }

    @Test
    fun successProfessSummary() {
        dataModel!!.txtProfSummary = "I am android developer"
        TestCase.assertEquals(true, presenter!!.isValidProfessionSummary())
        verify(view)!!.showError()
    }

    @Test
    fun failureProfessSummary() {
        dataModel!!.txtProfSummary = ""
        TestCase.assertEquals(false,  presenter!!.isValidProfessionSummary())
    }

}
