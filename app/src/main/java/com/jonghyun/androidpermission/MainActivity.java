package com.jonghyun.androidpermission;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();
    }

    private void getPermission() {
        Permission
                .initializeReq(this)
                .setReqCode(200)
                .setCompleteCallback(() -> {
                })
                .setTarget(Manifest.permission.ACCESS_WIFI_STATE)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permission
                .initializeRes(this)
                .setReqCode(200)
                .setGrantedCallback(() -> {
                })
                .setDeniedCallback(permission ->
                        PermissionDialog
                                .initialize(this)
                                .setConfirmListener("Yes", (dialog, which) -> {}) // retry get permission ex ) getPermission()
                                .setCancelListener("No", (dialog, which) -> {}) // not use permission error callback
                                .show("Denied message"))
                .response(requestCode, permissions, grantResults);
    }
}
