package com.vpc3.personalexpensesapp.activites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.activites.model.Expenses;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    public BottomSheetFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.bottom_sheet, container, false);
        EditText place = v.findViewById(R.id.enterPlace);
        EditText date = v.findViewById(R.id.dateEt);
        EditText amountEt = v.findViewById(R.id.amountEt);
        Button saveExpenses = v.findViewById(R.id.saveBtn);
        saveExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String placeSt = place.getText().toString();
                String date1 = date.getText().toString();
                String amount = amountEt.getText().toString();
                Expenses e = new Expenses(placeSt,date1,Double.parseDouble(amount));
            }
        });

        return v;
    }
}