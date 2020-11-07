package com.vpc3.personalexpensesapp.activites.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.activites.model.Expenses;

import java.util.ArrayList;

public class ExpensesAdapter  extends  RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder>{

    private Context context;
    private ArrayList<Expenses> arrayList;

    public ExpensesAdapter(Context context, ArrayList<Expenses> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_expenses_row,parent,false);
        return new ExpensesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position) {
       holder.place.setText(arrayList.get(position).getPlace());
        holder.date.setText(arrayList.get(position).getDate());
        holder.money.setText(String.valueOf(arrayList.get(position).getMoney()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ExpensesViewHolder extends RecyclerView.ViewHolder{

        TextView place,money,date;
        public ExpensesViewHolder(@NonNull View itemView) {
            super(itemView);
            place = itemView.findViewById(R.id.placeTv);
            money = itemView.findViewById(R.id.moneyTv);
            date = itemView.findViewById(R.id.dateTv);
        }
    }
}
