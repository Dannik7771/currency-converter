package com.drownedman.currencyconverter.presentation.repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;

import java.util.List;

public interface RepositoryTasks {
    <T extends CurrencyValue> void addCurrencyValue(T currencyValue);
    <T extends CurrencyValue> LiveData<List<T>> getAllCurrencyValues(LifecycleOwner owner);
}
