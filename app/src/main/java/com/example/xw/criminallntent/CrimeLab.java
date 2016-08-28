package com.example.xw.criminallntent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by xw on 2016/8/26.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

    public List<Crime> getCrimes() {
        return mCrimes;
    }

  public Crime getCrime(UUID id){
      for (Crime crime:
           mCrimes) {
          if (crime.getId().equals(id))
              return crime;
      }
      return null;
  }

    private CrimeLab(Context context) {
        mCrimes=new ArrayList<>();

    }

    public  static CrimeLab get(Context context){
        if(sCrimeLab==null){
            sCrimeLab=new CrimeLab(context);
        }
        return sCrimeLab;
    }
    public void addCrime(Crime c){
        mCrimes.add(c);
    }
}
