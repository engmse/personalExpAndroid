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
import com.vpc3.personalexpensesapp.Common;
import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.model.Expenses;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    interface ExpensesCallBack{
        void onExpensesAdded(Expenses e);
    }
    private ExpensesCallBack expensesCallBack;
    public BottomSheetFragment(ExpensesCallBack expensesCallBack) {
        this.expensesCallBack = expensesCallBack;
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
                Expenses e = new Expenses();
                e.setDate(date1);
                e.setMoney(Double.parseDouble(amount));
                e.setPlace(placeSt);
                e.setUid(Common.user.getUid());
                expensesCallBack.onExpensesAdded(e);
                dismiss();
            }
        });

        return v;
    }
}