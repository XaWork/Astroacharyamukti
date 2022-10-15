package com.example.astroacharyamukti.api;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String BASE_URL = "https://theacharyamukti.com/";
    private static Retrofit retrofit;
    private static RetrofitClient retrofitClient;

    private RetrofitClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }
    private static synchronized RetrofitClient getInstance(){
        if (retrofitClient==null){
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }
    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }

}
