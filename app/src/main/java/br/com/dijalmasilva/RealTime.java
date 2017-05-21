package br.com.dijalmasilva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class RealTime extends AppCompatActivity {

    private boolean flash = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time);
        getSupportActionBar().setTitle(getResources().getString(R.string.tittle_realtime));

        final ImageButton btFlash = (ImageButton) findViewById(R.id.bt_flash);

        btFlash.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flash) {
                    Toast.makeText(getApplicationContext(), "Flash desligado!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Flash ligado!",
                            Toast.LENGTH_SHORT).show();
                }

                flash = !flash;
            }
        });
    }
}
