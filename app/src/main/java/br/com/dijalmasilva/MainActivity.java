package br.com.dijalmasilva;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.dijalmasilva.pdm.models.WebCam;
import br.com.dijalmasilva.services.WebCamService;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_INTERNET_RESULT = 0;
    private static final int REQUEST_ACCESS_NETWORK_STATE_RESULT = 1;

    private void startWebCam() {
        final WebCamService webCamService = new WebCamService(this);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final WebCam webCam = webCamService.registerWebCam();
                Intent intent = new Intent(MainActivity.this, CamRecord.class);
                intent.putExtra("CamActivity", webCam);
                startActivity(intent);
            }
        };
        new Thread(runnable).start();
    }

    private void startControlSetting() {
        Intent intent = new Intent(MainActivity.this, ControlSetting.class);
        startActivity(intent);
    }

    private void checkPermissionInternet() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET_RESULT);
            }
        }
    }

    private void checkPermissionAccessNetwork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, REQUEST_ACCESS_NETWORK_STATE_RESULT);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getResources().getString(R.string.tittle_initial));

        Button button_cam_start = (Button) findViewById(R.id.button_cam_start);
        Button button_ctrl_setting = (Button) findViewById(R.id.button_ctrl_setting);

        button_cam_start.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWebCam();
            }
        });

        button_ctrl_setting.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startControlSetting();
            }
        });

        checkPermissionInternet();
        checkPermissionAccessNetwork();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_INTERNET_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "O aplicativo não funcionará sem acesso a internet!", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == REQUEST_ACCESS_NETWORK_STATE_RESULT) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "O aplicativo não funcionará sem acesso a internet!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
