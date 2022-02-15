package com.drownedman.currencyconverter.presentation.network.viewModel;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;
import com.drownedman.currencyconverter.presentation.repository.AppService;

import java.util.List;

public class CurrencyConverterViewModel extends ViewModel {
    public LiveData<List<CurrencyValue>> getAllCurrencyCodes(LifecycleOwner owner) {
        return AppService.getInstance().getConverterApi().getAllCurrencyValues(owner);
    }

    public void initCurrencyCodes() {
    }
}
