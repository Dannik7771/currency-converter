package com.drownedman.currencyconverter.presentation.repository.dto;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;

@Entity(tableName = "currency_value", primaryKeys = {"code"})
public class CurrencyValueDTO extends CurrencyValue {

    public CurrencyValueDTO() {
    }

    public CurrencyValueDTO(@NonNull String code, double value) {
        super(code, value);
    }
}
