package com.cvapp.assignment

import com.cvapp.assignment.contract.PersonalContract
import com.cvapp.assignment.models.EducationDataModel
import com.cvapp.assignment.presenter.EducationPresenter
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


/**
 * Created by Mathavan_K on 7/12/2019.
 */

class EducationPresenterTest {
    @Mock
    lateinit var  eduView: PersonalContract.View

    lateinit var educationDataModel: EducationDataModel

    @Mock
    lateinit var educationPresenter: EducationPresenter

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        educationDataModel = EducationDataModel("","","","")
        educationPresenter = EducationPresenter(eduView, educationDataModel)
    }

    @Test
    fun successEduInputFieldTest() {
        educationDataModel.univerty = "Anna University"
        educationDataModel.grade = "A"
        educationDataModel.course = "BE"
        educationDataModel.yop = "2009"
        TestCase.assertEquals(true,educationPresenter.isValidateInputField())
    }

    @Test
    fun failureEduInputFieldTest() {
        educationDataModel.univerty = ""
        educationDataModel.grade = "A"
        educationDataModel.course = "BE"
        educationDataModel.yop = ""
        educationPresenter.isValidateInputField()
        verify(eduView).showError()
    }

    @Test
    fun successUniverityFieldTest() {
        educationDataModel.univerty = "Anna Univerity"
        TestCase.assertEquals(true,educationPresenter.isValidUniversity())
    }
    @Test
    fun failureUniverityFieldTest() {
        educationDataModel.univerty = ""
        TestCase.assertEquals(false,educationPresenter.isValidUniversity())
    }

    @Test
    fun successCourseFieldTest() {
        educationDataModel.course= "BE"
        TestCase.assertEquals(true,educationPresenter.isValidUniversity())
    }
    @Test
    fun failureCourseFieldTest() {
        educationDataModel.course = ""
        TestCase.assertEquals(false,educationPresenter.isValidUniversity())
    }

    @Test
    fun successYopFieldTest() {
        educationDataModel.yop = "2009"
        TestCase.assertEquals(true,educationPresenter.isYop())
    }

    @Test
    fun failureYopFieldTest() {
        educationDataModel.yop = ""
        TestCase.assertEquals(false,educationPresenter.isYop())
    }

}
