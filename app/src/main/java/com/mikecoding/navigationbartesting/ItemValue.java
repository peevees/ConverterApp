package com.mikecoding.navigationbartesting;

import android.util.Log;

public class ItemValue {

    private String unit;
    private Double convertValue;

    public ItemValue(String unit, Double convertValue) {
        this.unit = unit;
        this.convertValue = convertValue;
        Log.d("TAG", unit + " " + convertValue);
    }

    public String getUnit() {
        return unit;
    }

    public Double getConvertValue() {
        return convertValue;
    }
}