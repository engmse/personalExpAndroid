package com.vpc3.personalexpensesapp.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vpc3.personalexpensesapp.R;

import com.vpc3.personalexpensesapp.adapter.ExpensesAdapter;
import com.vpc3.personalexpensesapp.api.ApiClient;
import com.vpc3.personalexpensesapp.api.ApiInterface;
import com.vpc3.personalexpensesapp.api.reponse.CommonResponse;
import com.vpc3.personalexpensesapp.api.reponse.ExpensesResponse;
import com.vpc3.personalexpensesapp.helper.Common;
import com.vpc3.personalexpensesapp.model.Expenses;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesActivity extends AppCompatActivity {
    private FloatingActionButton fcb;
    private TextView welcome;
    RecyclerView recyclerView;
    ArrayList<Expenses> expensesArrayList = new ArrayList<>();
    ExpensesAdapter adapter;
    private ProgressBar progressExpenses;
    SwipeRefreshLayout swipeRefreshLayout;
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
        progressExpenses = findViewById(R.id.progressExpenses);
        View appBar = findViewById(R.id.appBar);
        swipeRefreshLayout = findViewById(R.id.swapToRefresh);
        ImageView back = appBar.findViewById(R.id.backBtn);
        TextView title = appBar.findViewById(R.id.titleBar);
        ImageView settings = appBar.findViewById(R.id.settingBtn);
        back.setVisibility(View.GONE);
        title.setText(R.string.expenses);
        settings.setVisibility(View.INVISIBLE);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        progressExpenses.setVisibility(View.VISIBLE);
        getAllExpenses(Common.user.getId(),"");
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
                        dp.setText(i + "/" + (i1+1) + "/" + i2);
                        progressExpenses.setVisibility(View.VISIBLE);
                        getAllExpenses(Common.user.getId(),i + "-" + (i1+1) + "-" + i2);
                    }
                }, 2020, 11, 4);
                d.show();
            }
        });

        fcb.setOnClickListener(view -> {
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(new BottomSheetFragment.ExpensesCallBack() {
                @Override
                public void onExpensesAdded(Expenses e) {
                    progressExpenses.setVisibility(View.VISIBLE);
                    addExpenses(e);

                }
            });
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        });

        settings.setOnClickListener(view -> {
            startActivity(new Intent(ExpensesActivity.this,
                    SettingsActivity.class));
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllExpenses(Common.user.getId(),"");
                swipeRefreshLayout.setRefreshing(false);// Disables the refresh icon
            }
        });


    }

    private  void addExpenses(Expenses e){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
       Call<CommonResponse> expenses=apiInterface.addExpenses(e.getPlace(),
               e.getDate(),
               String.valueOf(e.getMoney()),
               Common.user.getId());
        expenses.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                String msg = response.body().getMsg();
                boolean opr = response.body().getOper();
                Toast.makeText(ExpensesActivity.this, msg, Toast.LENGTH_SHORT).show();
                if(opr){
                   adapter.addExpenses(e);
                }
                progressExpenses.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                Toast.makeText(ExpensesActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                progressExpenses.setVisibility(View.GONE);
            }
        });
    }

    private void getAllExpenses(String userId,String date){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ExpensesResponse> expensesResponseCall = apiInterface.getAllExpenses(userId,date);
        expensesResponseCall.enqueue(new Callback<ExpensesResponse>() {
            @Override
            public void onResponse(Call<ExpensesResponse> call, Response<ExpensesResponse> response) {
                if(response.body().getData().size()>0){
                    adapter = new ExpensesAdapter(ExpensesActivity.this, response.body().getData());
                    recyclerView.setAdapter(adapter);

                }else
                {
                    adapter.deleteAllData();
                    Toast.makeText(ExpensesActivity.this, "No Expenses Added", Toast.LENGTH_SHORT).show();
                }
                progressExpenses.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ExpensesResponse> call, Throwable t) {
                Toast.makeText(ExpensesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressExpenses.setVisibility(View.GONE);
            }
        });
    }
}