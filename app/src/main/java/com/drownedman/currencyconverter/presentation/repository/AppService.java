package com.drownedman.currencyconverter.presentation.repository;

import android.app.Application;

import com.drownedman.currencyconverter.presentation.network.ConverterRetrofitAPI;

public class AppService {
    private static AppService service;

    private RepositoryTasks repository;
    private ConverterRetrofitAPI converterApi;

    static public AppService getInstance() {
        if (service == null) {
            service = new AppService();
        }
        return service;
    }

    public RepositoryTasks getRepository() {
        if (repository == null) {
            repository = new CurrencyLocalRepository();
        }
        return repository;
    }

    public ConverterRetrofitAPI getConverterApi() {
        if (converterApi == null) {
            converterApi = new ConverterRetrofitAPI();
        }
        return converterApi;
    }
}
