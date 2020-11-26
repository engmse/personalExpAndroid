package com.vpc3.personalexpensesapp.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vpc3.personalexpensesapp.Common;
import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.adapter.ExpensesAdapter;
import com.vpc3.personalexpensesapp.model.Expenses;
import com.vpc3.personalexpensesapp.roomdb.RoomDatabaseSingleton;

import java.util.ArrayList;
import java.util.List;

public class ExpensesActivity extends AppCompatActivity {
    private FloatingActionButton fcb;
    private TextView welcome;
    RecyclerView recyclerView;
    ExpensesAdapter adapter;
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
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        getAllExpenses();
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
            com.vpc3.personalexpensesapp.activites.BottomSheetFragment bottomSheetFragment = new com.vpc3.personalexpensesapp.activites.BottomSheetFragment(new BottomSheetFragment.ExpensesCallBack() {
                @Override
                public void onExpensesAdded(Expenses e) {
                   saveExpenses(e);
                }
            });
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        });

        settings.setOnClickListener(view -> {
            startActivity(new Intent(ExpensesActivity.this,
                    com.vpc3.personalexpensesapp.activites.SettingsActivity.class));
        });


    }

    public void saveExpenses(Expenses e){
        class ExpensesTask extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                RoomDatabaseSingleton.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .getDao()
                        .addExpenses(e);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(ExpensesActivity.this, "Expenses Added Sucessfully", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        }
        new ExpensesTask().execute();
    }

    private  void getAllExpenses(){
        class allExpensesTask extends AsyncTask<Void,Void,Void>{
            ArrayList<Expenses> expensesList;
            @Override
            protected Void doInBackground(Void... voids) {
                expensesList=  (ArrayList<Expenses>) RoomDatabaseSingleton.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .getDao()
                        .getAllExpenses(Common.user.getUid());
                Log.d("Array_SIZE",""+ expensesList.size());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter = new ExpensesAdapter(getApplicationContext(), expensesList);
                recyclerView.setAdapter(adapter);
            }
        }
        new allExpensesTask().execute();
    }
}