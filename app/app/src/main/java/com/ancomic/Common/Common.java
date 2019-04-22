package com.ancomic.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info!=null)
            {
                    for (int i=0;i<info.length;i++)
                        if(info[i].getState()== NetworkInfo.State.CONNECTED)
                            return true;
            }

        }
        return  false;
    }
}
