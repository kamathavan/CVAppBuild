package com.cvapp.assignment

import com.cvapp.assignment.contract.ProfileContract
import com.cvapp.assignment.dataobjects.EducationDataObject
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
    lateinit var  eduView: ProfileContract.View


    lateinit var educationDataObject: EducationDataObject


    lateinit var educationPresenter: EducationPresenter

    @Before
    fun setUpPresenter() {
        MockitoAnnotations.initMocks(this)
        educationDataObject = EducationDataObject("", "", "", "")
        educationPresenter = EducationPresenter(eduView, educationDataObject)
    }

    @Test
    fun successEduInputFieldTest() {
        educationDataObject.univerty = "Anna University"
        educationDataObject.grade = "A"
        educationDataObject.course = "BE"
        educationDataObject.yop = "2009"
        TestCase.assertEquals(true,educationPresenter.isValidateInputField())
    }

    @Test
    fun failureEduInputFieldTest() {
        educationDataObject.univerty = ""
        educationDataObject.grade = "A"
        educationDataObject.course = "BE"
        educationDataObject.yop = ""
        TestCase.assertEquals(false,educationPresenter.isValidateInputField())
        verify(eduView).showError()
    }

    @Test
    fun successUniverityFieldTest() {
        educationDataObject.univerty = "Anna Univerity"
        TestCase.assertEquals(true,educationPresenter.isValidUniversity())
    }
    @Test
    fun failureUniverityFieldTest() {
        educationDataObject.univerty = ""
        TestCase.assertEquals(false,educationPresenter.isValidUniversity())
    }

    @Test
    fun successCourseFieldTest() {
        educationDataObject.course= "BE"
        TestCase.assertEquals(true,educationPresenter.isValidateCourse())
    }
    @Test
    fun failureCourseFieldTest() {
        educationDataObject.course = ""
        TestCase.assertEquals(false,educationPresenter.isValidateCourse())
    }

    @Test
    fun successYopFieldTest() {
        educationDataObject.yop = "2009"
        TestCase.assertEquals(true,educationPresenter.isYop())
    }

    @Test
    fun failureYopFieldTest() {
        educationDataObject.yop = ""
        TestCase.assertEquals(false,educationPresenter.isYop())
    }

}
