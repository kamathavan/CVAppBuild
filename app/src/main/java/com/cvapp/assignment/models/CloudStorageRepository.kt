package com.cvapp.assignment.models

import android.net.Uri

import com.cvapp.assignment.contract.UploadProfileContract
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

import java.io.File

import com.cvapp.assignment.utils.Constants.Companion.CLOUD_BUCKET_PATH

/**
 * Created by Mathavan_K on 7/8/2019.
 */

class CloudStorageRepository : UploadProfileContract.Models {

    lateinit var mStorageRef: StorageReference

    lateinit var mListener: UploadProfileContract.Models.OnListener

    /**
     * To handle the success listener for upload file
     */
    private val onSuccessListener = OnSuccessListener<UploadTask.TaskSnapshot> { mListener.onFinished() }
    /**
     * To handle the failur listener for upload file
     */
    private val onFailureListner = OnFailureListener { e -> mListener.onFailure(e) }
    /**
     * To handle the on progress listener for upload file
     */
    private val onProgressListener = OnProgressListener<UploadTask.TaskSnapshot> { taskSnapshot ->
        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
        mListener.onProgress(progress)
    }

    /**
     * upload file into the cloud store path
     * @param filepath
     * @param listener
     */
    override fun uploadProfile(filepath: String, listener: UploadProfileContract.Models.OnListener) {
        mListener = listener
        val filePathUri = Uri.fromFile(File(filepath))
        mStorageRef = FirebaseStorage.getInstance().reference
        val riversRef = mStorageRef.child(CLOUD_BUCKET_PATH)
        riversRef.putFile(filePathUri).addOnSuccessListener(onSuccessListener).addOnProgressListener(onProgressListener)
                .addOnFailureListener(onFailureListner)
    }
}
