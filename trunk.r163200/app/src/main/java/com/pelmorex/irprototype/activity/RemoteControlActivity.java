package com.pelmorex.irprototype.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.pelmorex.irprototype.R;
import com.pelmorex.irprototype.application.IRApplication;
import com.pelmorex.irprototype.ir.Remote;

public class RemoteControlActivity extends AppCompatActivity {
    private final Navigator navigator = new Navigator();
    private Remote remote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        buildUi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRemote();
    }

    private void updateRemote() {
        remote = new Remote(this, ((IRApplication) getApplicationContext()).getRemoteConfiguration());
    }

    private void buildUi() {
        setContentView(R.layout.activity_remote_control);
        findViewById(R.id.tv_on_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remote.turnOnTv();
            }
        });
        findViewById(R.id.tv_off_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remote.turnOffTv();
            }
        });
        findViewById(R.id.stb_power_toggle_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remote.powerToggleStb();
            }
        });
        findViewById(R.id.tv_twn_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remote.tuneTwnChannel();
            }
        });
        findViewById(R.id.tv_config_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateToRemoteConfiguration();
            }
        });
    }

    private class Navigator {
        private void exit() {
        }

        private void navigateToRemoteConfiguration() {
            startActivity(new Intent(RemoteControlActivity.this, RemoteConfigurationActivity.class));
        }
    }
}
