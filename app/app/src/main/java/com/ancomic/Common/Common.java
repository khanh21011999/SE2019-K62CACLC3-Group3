package com.ancomic.Common;

import com.ancomic.Model.Chapter;
import com.ancomic.Model.Comic;
import com.ancomic.Retrofit.IComicAPI;
import com.ancomic.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class Common {
    public static Comic selected_comic;
    public static Chapter selected_chapter;
    public static int chapter_index=-1;
    public static List<Chapter> chapterList=new ArrayList<>();
    public static IComicAPI getAPI(){
        return RetrofitClient.getInstance().create(IComicAPI.class);
    }
    public static String formatString(String name){
        StringBuilder finalResult=new StringBuilder(name.length()>15 ?name.substring(0,15)+"...":name );
        return finalResult.toString();
    }
}
