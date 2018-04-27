package com.balinasoft.data.entity.images;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteResponce {
    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("data")
    @Expose
    private String data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
