package com.example.xw.criminallntent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by xw on 2016/8/29.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION=1;
    private static final String DATABASE_NAME="crimeBase.db";
    public CrimeBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ CrimeDbSchema.CrimeTable.NAME+"("+
        "_id integer primary key autoincrement, "+
                CrimeDbSchema.CrimeTable.Cols.UUID+","+
              CrimeDbSchema.CrimeTable.Cols.TITLE+","+
        CrimeDbSchema.CrimeTable.Cols.DATE+","+
       CrimeDbSchema.CrimeTable.Cols.SOLVED+","+
                        CrimeDbSchema.CrimeTable.Cols.SUSPECT+
                ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
