package com.drownedman.currencyconverter.presentation.network;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.drownedman.currencyconverter.domain.model.CurrencyValue;
import com.drownedman.currencyconverter.presentation.repository.RepositoryTasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConverterRetrofitAPI {
    static class CurrencyTransferBody {
        String base_code;
        long time_last_update_unix;
        HashMap<String, Double> conversion_rates;

        public HashMap<String, Double> getConversion_rates() {
            return conversion_rates;
        }

        public void setConversion_rates(HashMap<String, Double> conversion_rates) {
            this.conversion_rates = conversion_rates;
        }

        public void setTime_last_update_unix(long time_last_update_unix) {
            this.time_last_update_unix = time_last_update_unix;
        }

        public long getTime_last_update_unix() {
            return time_last_update_unix;
        }

        public String getBase_code() {
            return base_code;
        }

        public void setBase_code(String base_code) {
            this.base_code = base_code;
        }
    }

    private CurrencyConvertAPI api;
    private static final String APIKEY = "bbdd190bf1c5d8acc3558df6";
    private static final String HOST = "v6.exchangerate-api.com/v6/"+APIKEY;// /latest/USD

    public ConverterRetrofitAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://"+HOST+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CurrencyConvertAPI.class);
    }

    public MutableLiveData<List<CurrencyValue>> getAllCurrencyValues(LifecycleOwner owner) {
        MutableLiveData<List<CurrencyValue>> currencyTransferValuesLiveData
                = new MutableLiveData<>();
        api.getCurrencyTransferValues("USD").enqueue(new Callback<CurrencyTransferBody>() {
            @Override
            public void onResponse(Call<CurrencyTransferBody> call, Response<CurrencyTransferBody> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    ArrayList<CurrencyValue> currencyTransferValues = new ArrayList();
                    for (String code : response.body().conversion_rates.keySet())
                        currencyTransferValues.add(new CurrencyValue(code,
                                response.body().conversion_rates.get(code)));
                    currencyTransferValuesLiveData.setValue(currencyTransferValues);
                    //TODO: здесь сохраняем на локалке
                }
            }

            @Override
            public void onFailure(Call<CurrencyTransferBody> call, Throwable t) {
                t.printStackTrace();
                //TODO: здесь извлекаем из локалки
            }
        });
        return currencyTransferValuesLiveData;
    }
}
