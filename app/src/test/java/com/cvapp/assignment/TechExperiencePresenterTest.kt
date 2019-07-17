package com.cvapp.assignment

import com.cvapp.assignment.contract.ProfileContract
import com.cvapp.assignment.dataobjects.TechSkillDataObject
import com.cvapp.assignment.presenter.TechExperiencePresenter

import junit.framework.TestCase

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import org.mockito.Mockito.verify

/**
 * Created by Mathavan_K on 7/15/2019.
 */

class TechExperiencePresenterTest {

    lateinit var dataObject: TechSkillDataObject

    @Mock
    lateinit var view: ProfileContract.View


    lateinit var presenter: TechExperiencePresenter

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        dataObject = TechSkillDataObject("", "", "")
        presenter = TechExperiencePresenter(view, dataObject)
    }

    @Test
    fun isInputFieldSuccess() {
        dataObject.profSummary = "I am android developer"
        dataObject.otherSkill = "9"
        dataObject.coreSkill = "Android"
        TestCase.assertEquals(true, presenter.isValidateInputField())
    }

    @Test
    fun isInputFieldFailure() {
        dataObject.profSummary = "I am android developer"
        dataObject.otherSkill = ""
        dataObject.coreSkill = ""
        TestCase.assertEquals(false, presenter.isValidateInputField())
        verify(view).showError()
    }

    @Test
    fun successCoreSkill() {
        dataObject.coreSkill = "Android"
        TestCase.assertEquals(true, presenter.isValidCoreSkill())
    }

    @Test
    fun failureCoreSkill() {
        dataObject.coreSkill = ""
        TestCase.assertEquals(false, presenter.isValidCoreSkill())
        //verify(view).showError()
    }

    @Test
    fun successProfessSummary() {
        dataObject.profSummary = "I am android developer"
        TestCase.assertEquals(true, presenter.isValidProfessionSummary())
      // verify(view).showError()
    }

    @Test
    fun failureProfessSummary() {
        dataObject.profSummary = ""
        TestCase.assertEquals(false,  presenter.isValidProfessionSummary())
    }

}
