package com.vpc3.personalexpensesapp.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.adapter.ExpensesAdapter;
import com.vpc3.personalexpensesapp.model.Expenses;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {
    private FloatingActionButton fcb;
    private TextView welcome;
    RecyclerView recyclerView;
    ArrayList<Expenses> expensesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_activty);
        initView();
    }


    private void initView() {
        TextView dp = findViewById(R.id.dataPicker);
        fcb = findViewById(R.id.floatingAddBtn);
        welcome = findViewById(R.id.welcomeTv);
        recyclerView = findViewById(R.id.recyclerExpenses);
        View appBar = findViewById(R.id.appBar);
        ImageView back = appBar.findViewById(R.id.backBtn);
        TextView title = appBar.findViewById(R.id.titleBar);
        ImageView settings = appBar.findViewById(R.id.settingBtn);

        back.setVisibility(View.GONE);
        title.setText(R.string.expenses);
        settings.setVisibility(View.INVISIBLE);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ExpensesAdapter adapter = new ExpensesAdapter(this, expensesArrayList);
        recyclerView.setAdapter(adapter);
        /*************************************************/
        Bundle b = getIntent().getExtras();
        if (b != null) {
            welcome.setText("Welcome " + b.getString("KEY_UN"));
        }
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog d = new DatePickerDialog(ExpensesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dp.setText(i + "/" + i1 + "/" + i2);
                    }
                }, 2020, 11, 4);
                d.show();
            }
        });

        fcb.setOnClickListener(view -> {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(new BottomSheetFragment.ExpensesCallBack() {
                @Override
                public void onExpensesAdded(Expenses e) {
                    expensesArrayList.add(e);
                    adapter.notifyDataSetChanged();
                }
            });
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        });

        settings.setOnClickListener(view -> {
            startActivity(new Intent(ExpensesActivity.this,
                    SettingsActivity.class));
        });


    }
}