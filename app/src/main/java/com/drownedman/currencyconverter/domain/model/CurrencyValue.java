package com.drownedman.currencyconverter.domain.model;

import androidx.annotation.NonNull;

public class CurrencyValue {
    @NonNull
    private String code;
    private double value;

    public CurrencyValue() {}

    public CurrencyValue(@NonNull String code, double value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
