package com.cvapp.assignment.Presenter

import com.cvapp.assignment.contract.ProfileView
import com.cvapp.assignment.dataobjects.TechSkillDataObject
import com.cvapp.assignment.presenter.TechExperienceInfoPresenter
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by Mathavan_K on 7/18/2019.
 */

class TechSkillPresenterTest{

    @Mock
    lateinit var view: ProfileView


    lateinit var techSkillPrsenter: TechExperienceInfoPresenter

    lateinit var techSkillDo: TechSkillDataObject

    lateinit var  techSkillData :String;

    @Before
    fun setUpProsenter(){
        MockitoAnnotations.initMocks(this)
        techSkillPrsenter  = TechExperienceInfoPresenter(view)


    }

    @Test
    fun  onTechSkillSaveTest(){
        techSkillPrsenter.saveTechSkillInfo("Android",
                "9",
                "I am a Android application Developer")
    }

    @Test
    fun  onFailureTest(){
        techSkillPrsenter.onfailure()
        verify(view).showError()
    }
    @Test
    fun  onSuccessTest(){
        techSkillDo =
                TechSkillDataObject(
                        "Android",
                        "9",
                        "I am a android application developer"
                )
        val gson = Gson()
        techSkillData = gson.toJson(techSkillDo)
        techSkillPrsenter.onSuccess(techSkillData)
        verify(view).savePersonalData(techSkillData)
    }

}