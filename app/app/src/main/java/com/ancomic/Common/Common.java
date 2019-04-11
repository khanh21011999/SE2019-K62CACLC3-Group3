package com.ancomic.Common;

import com.ancomic.Retrofit.IComicAPI;
import com.ancomic.Retrofit.RetrofitClient;

import retrofit2.Retrofit;

public class Common {
    public static IComicAPI getAPI(){
        return RetrofitClient.getInstance().create(IComicAPI.class);
    }
}
