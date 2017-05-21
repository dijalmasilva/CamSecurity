package br.com.dijalmasilva.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by <a href="https://github.io/dijalmasilva" target="_blank">dijalma</a> on 20/05/17.
 */
public class DataBaseCore extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "camsecurity";
    private static final int VERSION = 1;

    public DataBaseCore(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table webcam( _id bigint not null primary key);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table webcam;");
        onCreate(sqLiteDatabase);
    }
}
