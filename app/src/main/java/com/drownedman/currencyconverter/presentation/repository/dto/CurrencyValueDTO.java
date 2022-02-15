package com.drownedman.currencyconverter.presentation.repository.dto;

import androidx.room.Entity;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;

@Entity(tableName = "currency_value", primaryKeys = {"code"})
public class CurrencyValueDTO extends CurrencyValue {

}
