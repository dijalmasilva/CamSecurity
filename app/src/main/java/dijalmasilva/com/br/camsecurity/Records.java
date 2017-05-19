package dijalmasilva.com.br.camsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Records extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        getSupportActionBar().setTitle(getResources().getString(R.string.tittle_records));
    }
}
