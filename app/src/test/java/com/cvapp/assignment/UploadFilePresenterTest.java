package com.cvapp.assignment;

import com.cvapp.assignment.contract.UploadProfileContract;
import com.cvapp.assignment.models.CloudStorageRepository;
import com.cvapp.assignment.presenter.UploadFilePresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Matchers.booleanThat;
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

    boolean flag;

    @Captor
    private ArgumentCaptor<UploadProfileContract.Models.OnListener> uploadFileListern;

    @Before
    public  void setUpPresenter(){
        MockitoAnnotations.initMocks(this);
        flag = true;
        uploadFilePresenter = new UploadFilePresenter(vw,model);
    }

    @Test
    public void onButtonClickTest(){

        String path = "/data/user/0/com.cvapp.assignment/files/profile.json";
        uploadFilePresenter.onButtonClick(path);
        Mockito.when(vw.isEnabled(true)).thenReturn(eq(flag));
        verify(vw).showProgressDialog(eq(0.0));
        Mockito.verify(model).uploadProfile(eq(path),uploadFileListern.capture());
        uploadFileListern.getValue().onFailure(new Throwable("Server may down."));
        uploadFileListern.getValue().onFinished();

    }
    @Test
    public void onFinishedTest() {
        String path = "/data/user/0/com.cvapp.assignment/files/profile.json";
        uploadFilePresenter.onButtonClick(path);
        Mockito.when(verify(vw).isEnabled(eq(true))).thenReturn(true);;
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
        uploadFilePresenter.onButtonClick(path);
        Mockito.when( verify(vw).isEnabled(eq(true))).thenReturn(true);
        verify(vw).showProgressDialog(eq(0.0));
        Mockito.verify(model).uploadProfile(eq(path),uploadFileListern.capture());
        uploadFileListern.getValue().onFailure(new Throwable("Server may down."));
        uploadFileListern.getValue().onFinished();
        verify(vw).hideProgressDialog();
        verify(vw).showFailureMsg();
    }



}
