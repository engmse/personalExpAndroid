package com.vpc3.personalexpensesapp.activites.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.vpc3.personalexpensesapp.R;


public class SettingFragment extends Fragment {

    Switch aSwitch;
    RadioButton rArabic, rEnglish;
    SeekBar seekBar;
    Button button;
    SharedPreferences preferences;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting_f_ragment, container, false);
        aSwitch = v.findViewById(R.id.switch_noti);
        rArabic = v.findViewById(R.id.arabic);
        rEnglish = v.findViewById(R.id.english);
        seekBar = v.findViewById(R.id.brightness);
        button = v.findViewById(R.id.btnSave);
        preferences = getActivity().getSharedPreferences("PREF_SETTINGS", Context.MODE_PRIVATE);
        button.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("KEY_NOTI", aSwitch.isChecked());
            editor.putInt("KEY_BRIGHT", seekBar.getProgress());
            editor.putInt("KEY_LANG", rArabic.isChecked() ? 1 : 0);
            editor.apply();
            Toast.makeText(getActivity(), "Settings Saved Successfully", Toast.LENGTH_SHORT).show();
        });
        getSettings();
        return v;
    }


    private void getSettings() {
        aSwitch.setChecked(preferences.getBoolean("KEY_NOTI", false));
        seekBar.setProgress(preferences.getInt("KEY_BRIGHT", 10));
        if (preferences.getInt("KEY_LANG", 1) == 1) {
            rArabic.setChecked(true);
        } else {
            rEnglish.setChecked(true);
        }
    }
}