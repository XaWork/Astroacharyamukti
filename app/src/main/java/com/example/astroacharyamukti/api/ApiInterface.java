package com.example.astroacharyamukti.api;

import com.example.astroacharyamukti.model.DataModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("astrologer/loginapi")
    Call<DataModel> login(@Body DataModel login);

}
