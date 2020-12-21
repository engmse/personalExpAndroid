package com.vpc3.personalexpensesapp.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.fragments.ProfileFragment;
import com.vpc3.personalexpensesapp.fragments.SettingFragment;

public class SettingsActivity extends LocalizationActivity {

    BottomNavigationView bottomNavigationView;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switchFragment(new SettingFragment());
        preferences = getSharedPreferences("PREF_SETTINGS",MODE_PRIVATE);
        int lang= preferences.getInt("KEY_LANG", 1);
        if(lang==1){
            setLanguage("ar");
        }else{
            setLanguage("en");
        }
        bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.setting_nav:
                        switchFragment(new SettingFragment());
                        break;
                    case R.id.profile_nav :
                        switchFragment(new ProfileFragment());

                }
                return true;
            }
        });
    }

    private void switchFragment(Fragment f){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container,f).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}