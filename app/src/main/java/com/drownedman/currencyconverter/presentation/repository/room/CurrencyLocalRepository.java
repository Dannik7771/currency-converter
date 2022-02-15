package com.drownedman.currencyconverter.presentation.repository.room;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;
import com.drownedman.currencyconverter.presentation.repository.RepositoryTasks;
import com.drownedman.currencyconverter.presentation.repository.dao.CurrencyValueDAO;
import com.drownedman.currencyconverter.presentation.repository.dto.CurrencyValueDTO;

import java.util.List;

public class CurrencyLocalRepository implements RepositoryTasks {
    private CurrencyValueDAO currencyValueDAO;

    public CurrencyLocalRepository(Application app) {
        CurrencyLocalRoomDatabase db = CurrencyLocalRoomDatabase.getDatabase(app);
        currencyValueDAO = db.currencyValueDAO();
    }

    @Override
    public void addCurrencyValue(CurrencyValue currencyValue) {
        currencyValueDAO.addCurrencyValue((CurrencyValueDTO) currencyValue);
    }

    @Override
    public LiveData<List<CurrencyValue>> getAllCurrencyValues(LifecycleOwner owner) {
        MutableLiveData<List<CurrencyValue>> result = new MutableLiveData<>();
        currencyValueDAO.getAllCurrencyValues().observe(owner, result::setValue);
        return result;
    }
}
