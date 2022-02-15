package com.drownedman.currencyconverter.presentation.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;
import com.drownedman.currencyconverter.presentation.repository.dto.CurrencyValueDTO;

import java.util.List;

@Dao
public interface CurrencyValueDAO {
    @Update
    void addCurrencyValue(CurrencyValueDTO currencyValue);

    @Query("SELECT * FROM currency_value")
    LiveData<List<CurrencyValueDTO>> getAllCurrencyValues();
}
