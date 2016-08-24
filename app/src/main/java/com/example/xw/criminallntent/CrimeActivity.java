package com.example.xw.criminallntent;



;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class CrimeActivity extends FragmentActivity {
    private Crime mCrime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        FragmentManager fm=getSupportFragmentManager();
       Fragment fragment= fm.findFragmentById(R.id.fragment_container);
        if (fragment==null){
            fragment =new CrimeFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

    }

}
