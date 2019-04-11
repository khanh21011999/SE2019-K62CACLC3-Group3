package com.ancomic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ancomic.Adapter.MySliderAdapter;
import com.ancomic.Common.Common;
import com.ancomic.Model.Banner;
import com.ancomic.Retrofit.IComicAPI;
import com.ancomic.Service.PicassoImageLoadingService;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity {

    Slider slider;
    IComicAPI iComicAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onStop(){
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init APT
         iComicAPI = Common.getAPI();

        //View
        slider = (Slider)findViewById(R.id.banner_slider);
        slider.init(new PicassoImageLoadingService());

        fetchBanner();
    }

    private void fetchBanner() {
        compositeDisposable.add(iComicAPI.getBannerList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Banner>>() {
                @Override
                public void accept(List<Banner> banners) throws Exception {
                    slider.setAdapter(new MySliderAdapter(banners));
                }
            },  new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Error while loading banner", Toast.LENGTH_SHORT).show();
                    }
                })
            );


    }
}
