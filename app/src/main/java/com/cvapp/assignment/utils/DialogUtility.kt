package com.cvapp.assignment.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

import com.cvapp.assignment.R


/**
 * Created by Mathavan_K
 */

object DialogUtility {

    var dialog: ProgressDialog? = null

    /**
     * show progress loader dialog
     * @param activity
     */
    fun showLoader(activity: Activity, progress: Double?) {
        if (dialog == null) {
            dialog = ProgressDialog(activity)
            dialog!!.setMessage(activity.resources.getString(R.string.app_loading) + "(" + progress!!.toInt()+ "%)")
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.setCancelable(false)
        }
        dialog!!.show()
    }

    /**
     * close the progress dialog
     */
    fun hideLoader() {
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
            dialog = null
        }
    }

    /**
     * show error alert dialog
     * @param msg
     * @param ctx
     */
    fun showSuccessAlert(msg: String, ctx: Activity) {
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle(ctx.resources.getString(R.string.app_msg))
        builder.setMessage(msg)
        builder.setCancelable(false)
        builder.setPositiveButton("OK") { dialog, id -> dialog.dismiss() }
        val alert11 = builder.create()
        alert11.show()
    }
}
