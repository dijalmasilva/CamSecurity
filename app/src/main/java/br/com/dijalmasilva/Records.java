package br.com.dijalmasilva;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.dijalmasilva.pdm.forms.RecordVO;

public class Records extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        getSupportActionBar().setTitle(getResources().getString(R.string.tittle_records));

        ListView listView = (ListView) findViewById(R.id.records);
        List<RecordVO> records = new ArrayList<>();
        records.add(new RecordVO());
        records.add(new RecordVO());
        records.add(new RecordVO());
        RecordListAdapter recordListAdapter = new RecordListAdapter(this, records);
        listView.setAdapter(recordListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Não faz nada ainda!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
