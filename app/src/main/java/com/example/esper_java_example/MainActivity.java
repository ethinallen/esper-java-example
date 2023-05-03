package com.example.esper_java_example;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;

import java.util.Arrays;
import androidx.appcompat.app.AppCompatActivity;
import io.esper.devicesdk.EsperDeviceSDK;

public class MainActivity extends AppCompatActivity {
    private EsperDeviceSDK sdk;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getDeviceTemperatures();
    }

    public void getDeviceTemperatures() {

        sdk = EsperDeviceSDK.getInstance(getApplicationContext());
        Log.d(TAG, "SDK Initiated Successfully.");

        // Add api token from API Key Management in your esper tenant
        String token = "";
        sdk.activateSDK(token, new EsperDeviceSDK.Callback<Void>() {
            @Override
            public void onResponse(Void response) {
                Log.d(TAG, "SDK was successfully activated");
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "SDK activation failed", t);
            }
        });

        sdk.getDeviceTemperatures(0, 0, new EsperDeviceSDK.Callback<float[]>() {
            @Override
            public void onResponse(@Nullable float[] response) {
                Log.d(TAG, "getDeviceTemperatures successful. temperatures : " + Arrays.toString(response));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "getDeviceTemperatures failure. error : " + t.getMessage());
            }
        });
    }
}