package com.balinasoft.data.errors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse extends Exception {
    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("error")
    @Expose
    private String error;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
