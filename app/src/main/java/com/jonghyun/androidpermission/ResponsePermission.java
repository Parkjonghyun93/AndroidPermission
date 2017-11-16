package com.jonghyun.androidpermission;

import android.app.Activity;
import android.content.pm.PackageManager;

import java.util.ArrayList;

/**
 * Created by parkjonghyun on 2017. 11. 15..
 */

public class ResponsePermission extends BasePermission {
    private int reqCode;
    private GrantedCallback grantedCallback;
    private DeniedCallback deniedCallback;

    public interface GrantedCallback {
        void fun1();
    }

    public interface DeniedCallback {
        void fun1(ArrayList<String> permissions);
    }

    public ResponsePermission(Activity context) {
        this.context = context;
    }

    public ResponsePermission setReqCode(int reqCode) {
        this.reqCode = reqCode;
        return this;
    }

    public ResponsePermission setDeniedCallback(DeniedCallback deniedCallback) {
        this.deniedCallback = deniedCallback;

        return this;
    }

    public ResponsePermission setGrantedCallback(GrantedCallback grantedCallback) {
        this.grantedCallback = grantedCallback;

        return this;
    }


    public void response(int reqCode, String[] permissions, int[] grantResults) {
        if (this.reqCode != reqCode) {
            return;
        }

        if (permissions == null && permissions.length <= 0
                && grantResults == null && grantResults.length <= 0) {
            return;
        }

        ArrayList<String> deniedPermission = new ArrayList<>();
        for (int index = 0; index < permissions.length; index++) {
            if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                deniedPermission.add(permissions[index]);
            }
        }

        if (deniedPermission.size() > 0) {
            deniedCallback.fun1(deniedPermission);
        } else {
            grantedCallback.fun1();
        }

    }

}
