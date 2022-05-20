package com.example.astroacharyamukti.api;

import com.example.astroacharyamukti.model.DataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("appapi/conection")
    Call<DataModel> generateOTP(@Body DataModel generateOTPRequest);

}
