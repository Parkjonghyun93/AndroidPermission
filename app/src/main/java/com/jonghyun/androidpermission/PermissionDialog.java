package com.jonghyun.androidpermission;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by parkjonghyun on 2017. 11. 16..
 */


public class PermissionDialog {
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    public PermissionDialog(AlertDialog.Builder builder) {
        this.builder = builder;
    }

    public PermissionDialog setConfirmListener(String message, DialogInterface.OnClickListener confirmListener) {
        builder.setPositiveButton(message, confirmListener);
        return this;
    }

    public PermissionDialog setCancelListener(String message, DialogInterface.OnClickListener confirmListener) {
        builder.setNegativeButton(message, confirmListener);
        return this;
    }

    public static PermissionDialog initialize(Context context) {
        return new PermissionDialog(new AlertDialog.Builder(context, R.style.PermissionDialogTheme));
    }

    public void show(String message) {
        alertDialog = builder.create();
        alertDialog.setMessage(message);
        alertDialog.show();
    }

}