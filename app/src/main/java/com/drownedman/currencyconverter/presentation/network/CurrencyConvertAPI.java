package com.drownedman.currencyconverter.presentation.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface CurrencyConvertAPI {
    @GET("latest/{currency_code}")
    @Headers({
            "Accept: application/json"
    })
    Call<ConverterRetrofitAPI.CurrencyTransferBody> getCurrencyTransferValues(
            @Path("currency_code") String currencyCode);
}
