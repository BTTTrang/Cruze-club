package com.test.cruzeclub;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GlobalVar {
    public static Retrofit retrofit;
    public static CruzeHolderAPI service;
    public static void InitRetrofit() {
         retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.4/api/")
                 .addConverterFactory(ScalarsConverterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(CruzeHolderAPI.class);
    }
}
