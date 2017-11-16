package com.jonghyun.androidpermission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

/**
 * Created by parkjonghyun on 2017. 11. 15..
 */

@TargetApi(Build.VERSION_CODES.M)
public class Permission {
    public static RequestPermission initializeReq(Activity context) {
        return new RequestPermission(context);
    }
    public static ResponsePermission initializeRes(Activity context) {
        return new ResponsePermission(context);
    }
}
