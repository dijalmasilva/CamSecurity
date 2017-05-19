package dijalmasilva.com.br.camsecurity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private void startWebCam() {
        Intent intent = new Intent(MainActivity.this, CamRecord.class);
        startActivity(intent);
    }

    private void startControlSetting() {
        Intent intent = new Intent(MainActivity.this, ControlSetting.class);
        startActivity(intent);
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
    }
}
