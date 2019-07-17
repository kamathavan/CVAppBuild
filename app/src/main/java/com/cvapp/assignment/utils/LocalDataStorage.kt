package com.cvapp.assignment.utils

import android.content.Context
import android.util.Log

import org.json.JSONException
import org.json.JSONObject

import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

import android.content.Context.MODE_PRIVATE
import com.cvapp.assignment.utils.Constants.Companion.FILE_NAME

/**
 * Created by Mathavan_K on 7/8/2019.
 */

class LocalDataStorage private constructor(private val context: Context) {

    /**
     * get the filepath
     * @return
     */
    val filePath: String
        get() = context.filesDir.toString() + "/" + FILE_NAME

    /**
     * save data into cloud storage as json file
     * @param jsonObject
     */
    fun saveData(jsonObject: String) {
        var fos: FileOutputStream? = null
        try {
            fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE)
            fos!!.write(jsonObject.toByteArray())
            Log.v("Filename----->", "Filename path---->" + context.filesDir + "/" + FILE_NAME)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    /**
     * delete the file
     */
    fun deleteFileFromLocal() {
        context.deleteFile(FILE_NAME)
    }

    companion object {

        private var single_instance: LocalDataStorage? = null
        /**
         * return the singleton instance of LocalDataStorage
         * @return
         */
        fun getInstance(ctx: Context): LocalDataStorage {
            if (single_instance == null)
                single_instance = LocalDataStorage(ctx)

            return single_instance as LocalDataStorage
        }
    }

}
