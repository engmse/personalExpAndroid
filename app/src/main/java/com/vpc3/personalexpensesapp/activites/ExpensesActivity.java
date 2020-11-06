package com.vpc3.personalexpensesapp.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vpc3.personalexpensesapp.R;

public class ExpensesActivity extends AppCompatActivity {
    private FloatingActionButton fcb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_activty);
        TextView dp = findViewById(R.id.dataPicker);
        fcb = findViewById(R.id.floatingAddBtn);
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog d = new DatePickerDialog(ExpensesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dp.setText(i+"/"+i1+"/"+i2);
                    }
                },2020,11,4);
                d.show();
            }
        });

        fcb.setOnClickListener(view -> {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        });


    }
}