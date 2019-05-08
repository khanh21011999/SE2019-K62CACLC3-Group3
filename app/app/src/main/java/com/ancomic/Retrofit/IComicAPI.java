package com.ancomic.Retrofit;

import com.ancomic.Model.Banner;
import com.ancomic.Model.Category;
import com.ancomic.Model.Chapter;
import com.ancomic.Model.Comic;
import com.ancomic.Model.Link;

import java.util.List;
import  io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IComicAPI {
    @GET("banner")
    Observable<List<Banner>> getBannerList();

    @GET("comic")
    Observable<List<Comic>> getComicList();

    @GET("chapter/{comicid}")
    Observable<List<Chapter>> getChapterList(@Path("comicid")int comicid);

    @GET("links/{chapterid}")
    Observable<List<Link>> getImageList(@Path("chapterid")int chapterid);

    @GET("categories")
    Observable<List<Category>> getCategoryList();

    @POST("filter")
    @FormUrlEncoded
    Observable<List<Comic>> getFilteredComic(@Field("data") String data) ;
    @POST("search")
    @FormUrlEncoded
    Observable<List<Comic>> getSearchedComic(@Field("search") String search) ;



}
