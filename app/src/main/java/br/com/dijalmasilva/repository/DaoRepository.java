package br.com.dijalmasilva.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 20/05/17.
 */

public class DaoRepository {

    private SQLiteDatabase database;

    public DaoRepository(Context context) {
        DataBaseCore dataBaseCore = new DataBaseCore(context);
        database = dataBaseCore.getWritableDatabase();
    }

    public void insert(Long idCam) {
        ContentValues values = new ContentValues();
        values.put("_id", idCam);
        database.insert("webcam", null, values);
    }

    public Long find() {
        String[] colums = new String[]{"_id"};
        final Cursor cursor = database
                .query(true, "webcam", colums, null, null, null, null, null, null);
        //
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getLong(0);
        }
        //
        return null;
    }

    public void delete() {
        database.delete("webcam", null, null);
    }
}
