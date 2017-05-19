package dijalmasilva.com.br.camsecurity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Record extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        getSupportActionBar().setTitle(getResources().getString(R.string.tittle_record));
    }
}
