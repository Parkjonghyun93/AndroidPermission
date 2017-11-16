package com.jonghyun.androidpermission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

/**
 * Created by parkjonghyun on 2017. 11. 15..
 */

public class RequestPermission extends BasePermission {
    public interface ErrorCallback {
        void onAction();
    }

    public interface CompleteCallback {
        void onAction();
    }
    private ErrorCallback errorCallback;
    private CompleteCallback completeCallback;
    private String[] permissions;
    private int reqCode;

    public RequestPermission(Activity context) {
        this.context = context;
    }

    public RequestPermission setTarget(String... permissions) {
        this.permissions = permissions;
        return this;
    }

    public RequestPermission setReqCode(int reqCode) {
        this.reqCode = reqCode;
        return this;
    }

    public RequestPermission setResumeCallback(ErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
        return this;
    }

    public RequestPermission setCompleteCallback(CompleteCallback completeCallback) {
        this.completeCallback = completeCallback;
        return this;
    }

    public void request() {
        ArrayList<String> permissionArray = permissionLogic();
        if (permissionArray == null) {
            return;
        }
        if (permissionArray.size() > 0) {
            requestPermission(permissionArray);
        } else {
            completeCallback.onAction();
        }
    }

    public void check() {
        ArrayList<String> permissionArray = permissionLogic();
        if (permissionArray == null) {
            return;
        }
        if (permissionArray.size() > 0 && errorCallback != null) {
            errorCallback.onAction();
        }
    }

    // private
    private ArrayList<String> permissionLogic() {
        if (permissions == null || permissions.length == 0) {
            return null;
        }

        ArrayList<String> permissionArray = new ArrayList<>();
        for (int index = 0; index < permissions.length; index++) {
            if (permissionGrantedCheck(permissions[index])) {
                permissionArray.add(permissions[index]);
            }
        }
        return permissionArray;
    }

    private boolean permissionGrantedCheck(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
    }

    private void requestPermission(ArrayList<String> permissions) {
        ActivityCompat.requestPermissions(context, permissions.toArray(new String[permissions.size()]), reqCode);
    }
}
