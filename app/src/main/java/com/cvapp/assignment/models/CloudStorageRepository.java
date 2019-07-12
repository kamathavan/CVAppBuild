package com.cvapp.assignment.models;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.cvapp.assignment.contract.UploadProfileContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import static com.cvapp.assignment.utils.Constants.CLOUD_BUCKET_PATH;

/**
 * Created by Mathavan_K on 7/8/2019.
 */

public class CloudStorageRepository implements UploadProfileContract.Models {

    private StorageReference mStorageRef;

    private OnListener mListener;

    /**
     * upload file into the cloud store path
     * @param filepath
     * @param listener
     */
    @Override
    public void uploadProfile(String filepath, final OnListener listener) {
        mListener = listener;
        Uri filePathUri = Uri.fromFile(new File(filepath));
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference riversRef = mStorageRef.child(CLOUD_BUCKET_PATH);
        riversRef.putFile(filePathUri).addOnSuccessListener(onSuccessListener).
                                       addOnProgressListener(onProgressListener).
                                       addOnFailureListener(onFailureListner);
    }

    /**
     * To handle the success listener for upload file
     */
    private OnSuccessListener onSuccessListener = new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            mListener.onFinished();
        }
    };
    /**
     * To handle the failur listener for upload file
     */
    private OnFailureListener onFailureListner = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            mListener.onFailure(e);
        }
    };
    /**
     * To handle the on progress listener for upload file
     */
    private OnProgressListener onProgressListener = new OnProgressListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
            mListener.onProgress(progress);
        }
    };
}
