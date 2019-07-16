package com.cvapp.assignment

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.TechSkillDataModel
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

    lateinit var dataModel: TechSkillDataModel

    @Mock
    lateinit var view: PersonalContract.View

    @Mock
    lateinit var presenter: TechExperiencePresenter

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        dataModel = TechSkillDataModel("","","")
        presenter = TechExperiencePresenter(view, dataModel)
    }

    @Test
    fun isInputFieldSuccess() {
        dataModel.profSummary = "I am android developer"
        dataModel.otherSkill = "9"
        dataModel.coreSkill = "Android"
        TestCase.assertEquals(true, presenter.isValidateInputField())
    }

    @Test
    fun isInputFieldFailure() {
        dataModel.profSummary = "I am android developer"
        dataModel.otherSkill = ""
        dataModel.coreSkill = ""
        TestCase.assertEquals(false, presenter.isValidateInputField())
        verify(view).showError()
    }

    @Test
    fun successCoreSkill() {
        dataModel.coreSkill = "Android"
        TestCase.assertEquals(true, presenter.isValidCoreSkill())
    }

    @Test
    fun failureCoreSkill() {
        dataModel.coreSkill = ""
        TestCase.assertEquals(false, presenter.isValidCoreSkill())
        //verify(view).showError()
    }

  /*  @Test
    fun successProfessSummary() {
        expDataModel.profSummary = "I am android developer"
        TestCase.assertEquals(true, presenter.isValidProfessionSummary())
        verify(view).showError()
    }

    @Test
    fun failureProfessSummary() {
        expDataModel.profSummary = ""
        TestCase.assertEquals(false,  presenter.isValidProfessionSummary())
    }*/

}
