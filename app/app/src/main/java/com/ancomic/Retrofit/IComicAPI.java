package com.ancomic.Retrofit;

import com.ancomic.Model.Banner;
import com.ancomic.Model.Comic;

import java.util.List;
import  io.reactivex.Observable;
import retrofit2.http.GET;

public interface IComicAPI {
    @GET("banner")
    Observable<List<Banner>> getBannerList();

    @GET("comic")
    Observable<List<Comic>> getComicList();

}
