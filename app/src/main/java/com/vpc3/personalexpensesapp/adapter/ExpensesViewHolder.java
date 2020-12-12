package com.vpc3.personalexpensesapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.model.Expenses;

public class ExpensesViewHolder extends RecyclerView.ViewHolder{

    TextView place,money,date;
    ImageView remove;
    public ExpensesViewHolder(@NonNull View itemView) {
        super(itemView);
        place = itemView.findViewById(R.id.placeTv);
        money = itemView.findViewById(R.id.moneyTv);
        date = itemView.findViewById(R.id.dateTv);
        remove = itemView.findViewById(R.id.removeBtn);
    }

    public void setData(Expenses e){
        place.setText(e.getPlace());
        money.setText(""+e.getMoney());
        date.setText(e.getDate());
    }
}