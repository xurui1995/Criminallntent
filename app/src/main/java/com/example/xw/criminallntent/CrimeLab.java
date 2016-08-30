package com.example.xw.criminallntent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.xw.criminallntent.database.CrimeBaseHelper;
import com.example.xw.criminallntent.database.CrimeCursorWrapper;
import com.example.xw.criminallntent.database.CrimeDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xw on 2016/8/26.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public List<Crime> getCrimes() {
        List<Crime> crimes=new ArrayList<Crime>();
        CrimeCursorWrapper cursor=queryCrimes(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return crimes;
    }

  public Crime getCrime(UUID id){
      CrimeCursorWrapper cursor=queryCrimes(CrimeDbSchema.CrimeTable.Cols.UUID+" =?",
              new String[]{id.toString()});
      try{
          if(cursor.getCount()==0){
              return null;
          }
          cursor.moveToFirst();
          return cursor.getCrime();
      }finally {
          cursor.close();
      }

  }

    private CrimeLab(Context context) {

        mContext=context.getApplicationContext();
        mDatabase=new CrimeBaseHelper(mContext).getWritableDatabase();

    }

    public  static CrimeLab get(Context context){
        if(sCrimeLab==null){
            sCrimeLab=new CrimeLab(context);
        }
        return sCrimeLab;
    }
    public void addCrime(Crime c){
        ContentValues values=getContentValues(c);
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME,null,values);

    }
    public void updateCrime(Crime crime){
        String uuidString=crime.getId().toString();
        ContentValues values=getContentValues(crime);
        mDatabase.update(CrimeDbSchema.CrimeTable.NAME,values, CrimeDbSchema.CrimeTable.Cols.UUID +"= ?",new String[]{uuidString});
    }
    private static ContentValues getContentValues(Crime crime){
        ContentValues values=new ContentValues();
        values.put(CrimeDbSchema.CrimeTable.Cols.UUID,crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.TITLE,crime.getTitle());
        values.put(CrimeDbSchema.CrimeTable.Cols.DATE,crime.getDate().getTime());
        values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED,crime.isSolved()?1:0);
        values.put(CrimeDbSchema.CrimeTable.Cols.SUSPECT,crime.getSuspect());
        return values;
    }
    private CrimeCursorWrapper queryCrimes(String whereClause,String[] whereArgs){
        Cursor cursor=mDatabase.query(CrimeDbSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,null,null);
        return new CrimeCursorWrapper(cursor);
    }
    public File getPhotoFile(Crime crime){
        File extranlFilesDir=mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (extranlFilesDir==null)
            return null;
        return new File(extranlFilesDir,crime.getPhotoFilename());
    }
}
