package com.cvapp.assignment.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;
import static com.cvapp.assignment.utils.Constants.FILE_NAME;

/**
 * Created by Mathavan_K on 7/8/2019.
 */

public class LocalDataStorage {

    private Context context;

    private static LocalDataStorage single_instance = null;

    JSONObject jsonObject = new JSONObject();

    private LocalDataStorage(Context ctx) {
        this.context = ctx;
    }
    /**
     *  return the singleton instance of LocalDataStorage
     * @return
     */
    public static LocalDataStorage getInstance(Context ctx) {
        if (single_instance == null)
            single_instance = new LocalDataStorage(ctx);

        return single_instance;
    }

    /**
     * save data into cloud storage as json file
     * @param jsonObject
     */
    public void saveData(String jsonObject){
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(jsonObject.toString().getBytes());
            Log.v("Filename----->", "Filename path---->" + context.getFilesDir() + "/" + FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * get the filepath
     * @return
     */
    public String getFilePath(){
        return context.getFilesDir()+"/"+FILE_NAME;
    }

    /**
     * delete the file
     */
    public void deleteFileFromLocal(){
           context.deleteFile(FILE_NAME);
    }

}
