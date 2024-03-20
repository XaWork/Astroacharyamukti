package com.acharyamukti.astrologer.api;

import com.acharyamukti.astrologer.model.AppVersionModel;
import com.acharyamukti.astrologer.model.BankData;
import com.acharyamukti.astrologer.model.FilterModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
    @GET("appapi/items/read.php")
    Call<BankData> getBankDetails(
            @Query("acharid") String userid
    );

    @GET("API/get-app-version")
    Call<AppVersionModel> getAppVersion();
}
