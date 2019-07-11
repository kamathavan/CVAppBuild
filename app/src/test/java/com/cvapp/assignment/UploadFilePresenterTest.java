package com.cvapp.assignment;

import com.cvapp.assignment.contract.UploadProfileContract;
import com.cvapp.assignment.models.CloudStorageRepository;
import com.cvapp.assignment.presenter.UploadFilePresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @Captor
    private ArgumentCaptor<UploadProfileContract.Models.OnListener> uploadFileListern;

    @Before
    public  void setUpPresenter(){
        MockitoAnnotations.initMocks(this);
        uploadFilePresenter = new UploadFilePresenter(vw,model);
    }

    @Test
    public void onButtonClickTest(){
        String path = "/data/user/0/com.cvapp.assignment/files/profile.json";
        uploadFilePresenter.onButtonClick(path);
        verify(vw).isEnabled(true);
        verify(vw).showProgressDialog(0.0);
        verify(model).uploadProfile("",uploadFileListern.capture());
        uploadFileListern.getValue().onFailure(new Throwable());
        uploadFileListern.getValue().onFinished();
    }


}
