package com.if5b.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueData<T> {
    //    @SerializedName("int")
    private int success;
    private String message;
    // Array Statis dan List Dinamis
    private List<T> data;

    public int getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }
}
