package dijalmasilva.com.br.camsecurity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ControlSetting extends AppCompatActivity {

    private void recordOneMinute() {
        Toast.makeText(getApplicationContext(), "Gravação de um minuto solicitada!",
                Toast.LENGTH_SHORT).show();
    }

    private void seeRecords() {
        Intent intent = new Intent(ControlSetting.this, Records.class);
        startActivity(intent);
    }

    private void realTime() {
        Intent intent = new Intent(ControlSetting.this, RealTime.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_setting);
        getSupportActionBar().setTitle(getResources().getString(R.string.tittle_settings));

        Button recordOneMinute = (Button) findViewById(R.id.record_one_minute);
        Button seeRecords = (Button) findViewById(R.id.see_records);
        Button realTime = (Button) findViewById(R.id.real_time);

        recordOneMinute.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordOneMinute();
            }
        });

        seeRecords.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeRecords();
            }
        });

        realTime.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                realTime();
            }
        });
    }
}
