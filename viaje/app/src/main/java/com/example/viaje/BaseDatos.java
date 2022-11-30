package com.example.viaje;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper {
    static final String DB="viaje.DB";
    static final int version=1;
    public BaseDatos(@Nullable Context context){
        super(context,DB,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table viajecoti(CodigoViaje Text primary key," +
                "Cartagena text not null, SanAndres text not null," +
                "LaGuajira text not null, NumP numbernor not null, ValorPnumbernor not null, ValorTnumbernor not null )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE viajecoti");{
            onCreate(db);
        }
    }
}
