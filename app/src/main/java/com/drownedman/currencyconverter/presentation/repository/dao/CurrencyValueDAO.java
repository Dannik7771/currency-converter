package com.drownedman.currencyconverter.presentation.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;
import com.drownedman.currencyconverter.presentation.repository.dto.CurrencyValueDTO;

import java.util.List;

@Dao
public interface CurrencyValueDAO {
    @Insert
    void addCurrencyValue(CurrencyValueDTO currencyValue);

    @Query("SELECT * FROM currency_value")
    LiveData<List<CurrencyValue>> getAllCurrencyValues();
}
