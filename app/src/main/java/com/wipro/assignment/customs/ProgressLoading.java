package com.wipro.assignment.customs;

import android.app.ProgressDialog;
import android.content.Context;

import com.wipro.assignment.R;

/**
 * Created by varora on 28-08-2018.
 */

public class ProgressLoading {
    private Context mContext;
    private ProgressDialog mProgressDialog;

    public ProgressLoading(Context context) {
        this.mContext = context;
        this.mProgressDialog = new ProgressDialog(context, R.style.MyAlertDialogStyle);
    }

    public void onShow() {
        mProgressDialog.setMessage(mContext.getString(R.string.wait));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void dismiss() {
        mProgressDialog.dismiss();
    }
}