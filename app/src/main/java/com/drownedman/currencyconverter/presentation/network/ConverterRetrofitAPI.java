package com.drownedman.currencyconverter.presentation.network;

import androidx.lifecycle.MutableLiveData;

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
        public long getTime_next_update_unix() {
            return time_next_update_unix;
        }

        public void setTime_next_update_unix(long time_next_update_unix) {
            this.time_next_update_unix = time_next_update_unix;
        }

        long time_next_update_unix;

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

    public MutableLiveData<HashMap<String,Float>> getCurrencyTransferValues(String currencyCode) {
        MutableLiveData<HashMap<String,Float>> currencyTransferValuesLiveData
                = new MutableLiveData<>();
        api.getCurrencyTransferValues(currencyCode).enqueue(new Callback<CurrencyTransferBody>() {
            @Override
            public void onResponse(Call<CurrencyTransferBody> call, Response<CurrencyTransferBody> response) {
                if (response.isSuccessful() && response.body() !=null) {
                    HashMap<String,Float> values;
                    System.out.println(response.body().time_next_update_unix);
                }
            }

            @Override
            public void onFailure(Call<CurrencyTransferBody> call, Throwable t) {

            }
        });
        return currencyTransferValuesLiveData;
    }
}
