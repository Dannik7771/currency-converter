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
import java.util.List;

public class AppService {
    private static AppService service;

    private RepositoryTasks repository;

    private ConverterRetrofitAPI converterApi;

    private List<String> codes;

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
        codes = new ArrayList<>();
        for (CurrencyValue currencyValue : currencyValues)
            codes.add(currencyValue.getCode());
    }

    public List<String> getCodes() {
        return codes;
    }
}
