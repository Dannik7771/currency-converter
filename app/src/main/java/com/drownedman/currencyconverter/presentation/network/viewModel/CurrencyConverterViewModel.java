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
    public LiveData<List<CurrencyValue>> getCurrencyCodes(LifecycleOwner owner) {
        return AppService.getInstance().getConverterApi().getAllCurrencyValues(owner);
    }

    public void updateCurrencyCodes(List<CurrencyValue> currencyValues) {
        AppService.getInstance().updateCodes(currencyValues);
    }

    public double convertValue(double value, String code1, String code2) {
        double fac1 = AppService.getInstance().getCurrencyValuesMap().get(code1);
        double fac2 = AppService.getInstance().getCurrencyValuesMap().get(code2);
        return value * (fac2/fac1);
    }
}
