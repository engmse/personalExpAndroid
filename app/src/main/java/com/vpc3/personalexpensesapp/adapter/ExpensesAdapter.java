package com.vpc3.personalexpensesapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.vpc3.personalexpensesapp.R;
import com.vpc3.personalexpensesapp.model.Expenses;

import java.util.ArrayList;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder> {

    private Context context;
    private ArrayList<Expenses> arrayList;
    private  boolean isOpen = false;
    public ExpensesAdapter(Context context, ArrayList<Expenses> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_expenses_row, parent, false);
        return new ExpensesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position) {
        holder.place.setText(arrayList.get(position).getPlace());
        holder.date.setText(arrayList.get(position).getDate());
        holder.money.setText(String.valueOf(arrayList.get(position).getMoney()));
        holder.remove.setOnClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle(context.getString(R.string.confirm_text));
            alertDialog.setMessage(context.getString(R.string.dialog_msg));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    arrayList.remove(position);
                    notifyDataSetChanged();
                }
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        });

        holder.check.setOnClickListener(view -> {
            if(isOpen){
                holder.check.setImageResource(R.drawable.ic_baseline_check_24);
                isOpen=false;
            }else{
                holder.check.setImageResource(R.drawable.ic_baseline_check_red_24);
                isOpen=true;
            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ExpensesViewHolder extends RecyclerView.ViewHolder {

        TextView place, money, date;
        ImageView remove, check;

        public ExpensesViewHolder(@NonNull View itemView) {
            super(itemView);
            place = itemView.findViewById(R.id.placeTv);
            money = itemView.findViewById(R.id.moneyTv);
            date = itemView.findViewById(R.id.dateTv);
            remove = itemView.findViewById(R.id.removeBtn);
            check = itemView.findViewById(R.id.checkBtn);
        }
    }
}
