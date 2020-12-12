package com.vpc3.personalexpensesapp.activties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.adapter.ExpensesAdapter;
import com.vpc3.personalexpensesapp.adapter.ExpensesViewHolder;
import com.vpc3.personalexpensesapp.model.Expenses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpensesActivity extends AppCompatActivity {
    private FloatingActionButton fcb;
    private TextView welcome;
    RecyclerView recyclerView;
    ArrayList<Expenses> expensesArrayList = new ArrayList<>();
    FirebaseFirestore db;
    FirestoreRecyclerOptions<Expenses> options;
    FirestoreRecyclerAdapter<Expenses, ExpensesViewHolder> adapter;
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
        db = FirebaseFirestore.getInstance();
        populateExpensesFromFirestore();
        back.setVisibility(View.GONE);
        title.setText(R.string.expenses);
        settings.setVisibility(View.INVISIBLE);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
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
                   addExpensesToFirebase(e);
                }
            });
            bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
        });

        settings.setOnClickListener(view -> {
            startActivity(new Intent(ExpensesActivity.this,
                    SettingsActivity.class));
        });
    }

    private void addExpensesToFirebase(Expenses e) {
        Map<String, Object> map = new HashMap<>();
        map.put("place", e.getPlace());
        map.put("date", e.getDate());
        map.put("money", e.getMoney());
        db.collection("Expenses")
                .add(map)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(ExpensesActivity.this, "Expesnes Added", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ExpensesActivity.this, "Expenses Not Added", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void populateExpensesFromFirestore(){
        Query query = db.collection("Expenses");
        options = new FirestoreRecyclerOptions.Builder<Expenses>()
                .setQuery(query,Expenses.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<Expenses, ExpensesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position, @NonNull Expenses model) {
              holder.setData(model);
            }
            @NonNull
            @Override
            public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.rv_expenses_row,parent,false);
                return new ExpensesViewHolder(v);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
}