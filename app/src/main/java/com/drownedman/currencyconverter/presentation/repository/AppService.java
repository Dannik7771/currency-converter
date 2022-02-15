package com.drownedman.currencyconverter.presentation.repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;
import com.drownedman.currencyconverter.presentation.network.ConverterRetrofitAPI;
import com.drownedman.currencyconverter.presentation.repository.room.CurrencyLocalRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AppService {
    private static AppService service;

    private RepositoryTasks repository;

    private ConverterRetrofitAPI converterApi;

    private List<CurrencyValue> currencyValues;
    private List<String> codes;
    private HashMap<String, Double> currencyValuesMap;

    public AppService() {
        currencyValuesMap = new HashMap<>();
    }

    static public AppService getInstance() {
        if (service == null) {
            service = new AppService();
        }
        return service;
    }

    public void initLocalRepository(Application app) {
        if (repository == null) {
            repository = new CurrencyLocalRepository(app);
        }
    }

    public RepositoryTasks getRepository() {
        return repository;
    }

    public ConverterRetrofitAPI getConverterApi() {
        if (converterApi == null) {
            converterApi = new ConverterRetrofitAPI();
        }
        return converterApi;
    }

    public void updateCodes(List<CurrencyValue> currencyValues) {
        this.currencyValues = currencyValues;
        codes = new ArrayList<>();
        for (CurrencyValue currencyValue : currencyValues) {
            currencyValuesMap.put(currencyValue.getCode(), currencyValue.getValue());
            codes.add(currencyValue.getCode());
        }
        Collections.sort(codes);
    }

    public List<String> getCodes() {
        return codes;
    }

    public List<CurrencyValue> getCurrencyValues() {
        return currencyValues;
    }

    public HashMap<String, Double> getCurrencyValuesMap() {
        return currencyValuesMap;
    }
}
