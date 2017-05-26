package br.com.dijalmasilva;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import br.com.dijalmasilva.pdm.forms.RecordVO;
import br.com.dijalmasilva.pdm.models.RecordModel;
import br.com.dijalmasilva.services.RecordService;

public class Records extends AppCompatActivity {

    private int count = 0;
    private final RecordService recordFindService;

    public Records() {
        this.recordFindService = new RecordService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        getSupportActionBar().setTitle(getResources().getString(R.string.tittle_records));
        //
        final ListView listView = (ListView) findViewById(R.id.records);
        final List<RecordVO> records = new ArrayList<>();
        final RecordListAdapter recordListAdapter = new RecordListAdapter(this, records);
        listView.setAdapter(recordListAdapter);
        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final RecordVO record = records.get(i);
                if (record.getRecord() == null) {
                    Toast.makeText(getApplicationContext(), "Vídeo está sendo baixado!",
                            Toast.LENGTH_SHORT).show();
                    Runnable run = new Runnable() {
                        @Override
                        public void run() {
                            final File file = recordFindService.findRecordFileInServer(record.getNameWebCam(), record.getId());
                            record.setRecord(file);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Vídeo baixado!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    };
                    new Thread(run).start();
                } else {
                    final Uri uri = Uri.parse(record.getRecord().getAbsolutePath());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setDataAndType(uri, "video/mp4");
                    startService(intent);
                }
            }
        });
        if (count == 0) {
            Toast.makeText(getApplicationContext(), "Vídeos não baixados serão baixados ao clicar nele!", Toast.LENGTH_LONG).show();
            count++;
        }
        Runnable run = new Runnable() {
            @Override
            public void run() {
                final List<RecordVO> recordsFound = recordFindService.findRecords("CAM_E2KE5W-96");
                for (RecordVO r : recordsFound) {
                    addRecordVOAndUpdateList(r);
                }
            }

            private void addRecordVOAndUpdateList(RecordVO recordVO) {
                records.add(recordVO);
                updateListAdapter();
            }

            private void updateListAdapter() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(recordListAdapter);
                    }
                });
            }

        };

        new Thread(run).start();
    }
}
