package com.vpc3.personalexpensesapp.api.reponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegestrationResponse {

    @SerializedName("oper")
    @Expose
    private Boolean oper;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Boolean getOper() {
        return oper;
    }

    public void setOper(Boolean oper) {
        this.oper = oper;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}