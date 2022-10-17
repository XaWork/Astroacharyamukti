package com.example.astroacharyamukti.api;

import com.example.astroacharyamukti.model.BankData;
import com.example.astroacharyamukti.model.FilterModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("astrologer/loginapi")
    Call<FilterModel> getSearch(
            @Field("acharid") String userid,
            @Field("name") String name,
            @Field("date_from") String startDate,
            @Field("date_to") String endDate,
            @Field("month") String month
    );

    @FormUrlEncoded
    @POST("appapi/items/read.php")
    Call<BankData> getBankDetails(
            @Field("acharid") String userid
    );
}
