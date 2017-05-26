package br.com.dijalmasilva.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.com.dijalmasilva.enums.Constants;
import br.com.dijalmasilva.pdm.forms.RecordForm;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 21/05/17.
 */

public class RecordSendService extends IntentService {

    public RecordSendService() {
        super("RecordSend");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            try {
                final Bundle extras = intent.getExtras();
                RecordForm record = (RecordForm) extras.get("record");
                final File fileRecord = record.getRecord();
                Socket socket = new Socket(Constants.HOST.getValue(), 1090);
                Log.d("DEBUG_DIJALMA", "Enviando objeto...");
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(record);
                //
                if (fileRecord != null) {
                    FileInputStream inputStream = new FileInputStream(fileRecord);
                    byte[] b = new byte[1024];
                    Log.d("DEBUG_DIJALMA", "Enviando vídeo...");
                    while (true) {
                        int len = inputStream.read(b);
                        if (len < 0) {
                            inputStream.close();
                            break;
                        }
                        out.write(b);
                    }
                    out.close();
                    Log.d("DEBUG_DIJALMA", "Vídeo enviado!");
                }
                //
                Log.d("DEBUG_DIJALMA", "Gravação enviada!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
