package com.vpc3.personalexpensesapp.api.reponse;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vpc3.personalexpensesapp.model.*;
public class ExpensesResponse {

    @SerializedName("oper")
    @Expose
    private Boolean oper;
    @SerializedName("data")
    @Expose
    private List<Expenses> data = null;

    public Boolean getOper() {
        return oper;
    }

    public void setOper(Boolean oper) {
        this.oper = oper;
    }

    public List<Expenses> getData() {
        return data;
    }

    public void setData(List<Expenses> data) {
        this.data = data;
    }

}