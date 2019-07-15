package com.cvapp.assignment;

import com.cvapp.assignment.contract.UploadProfileContract;
import com.cvapp.assignment.models.CloudStorageRepository;
import com.cvapp.assignment.models.ExperienceDataModel;
import com.cvapp.assignment.models.ProjExperDataModel;
import com.cvapp.assignment.presenter.UploadFilePresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by Mathavan_K on 7/11/2019.
 */

public class UploadFilePresenterTest {

    @Mock
    UploadProfileContract.Views vw;

    @Mock
    CloudStorageRepository model;

    @Mock
    UploadFilePresenter uploadFilePresenter;

    @Mock
    UploadProfileContract.Models model1;


    ProjExperDataModel projExperDataModel;


    @Captor
    private ArgumentCaptor<UploadProfileContract.Models.OnListener> uploadFileListern;

    @Before
    public  void setUpPresenter(){
        MockitoAnnotations.initMocks(this);
        projExperDataModel = new ProjExperDataModel();
        uploadFilePresenter = new UploadFilePresenter(vw,model,projExperDataModel);
    }

    @Test
    public void onButtonClickTest(){

        String path = "/data/user/0/com.cvapp.assignment/files/profile.json";
        uploadFilePresenter.onUploadProfile(path);
        verify(vw).showProgressDialog(eq(0.0));
        Mockito.verify(model).uploadProfile(eq(path),uploadFileListern.capture());
        uploadFileListern.getValue().onFailure(new Throwable("Server may down."));
        uploadFileListern.getValue().onFinished();

    }
    @Test
    public void onFinishedTest() {
        String path = "/data/user/0/com.cvapp.assignment/files/profile.json";
        uploadFilePresenter.onUploadProfile(path);
        verify(vw).showProgressDialog(eq(0.0));
        Mockito.verify(model).uploadProfile(eq(path),uploadFileListern.capture());
        uploadFileListern.getValue().onFailure(new Throwable("Server may down."));
        uploadFileListern.getValue().onFinished();
        verify(vw).hideProgressDialog();
        verify(vw).showsuccessMsg();
        verify(vw).clearAllData();
    }

    @Test
    public void onFailureTest() {
        String path = "/data/user/0/com.cvapp.assignment/files/profile.json";
        uploadFilePresenter.onUploadProfile(path);
        verify(vw).showProgressDialog(eq(0.0));
        Mockito.verify(model).uploadProfile(eq(path),uploadFileListern.capture());
        uploadFileListern.getValue().onFailure(new Throwable("Server may down."));
        uploadFileListern.getValue().onFinished();
        verify(vw).hideProgressDialog();
        verify(vw).showFailureMsg();
    }

    @Test
    public void isValidRoleName(){
        projExperDataModel.setResponsibility("Hello");
        String role = projExperDataModel.getRole();
      //  Assert.assertTrue(uploadFilePresenter);
    }



}
