package com.example.ethiopis_kids;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class GettingDeviceToken extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String DeviceToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Device Token" ,DeviceToken);
    }
}
