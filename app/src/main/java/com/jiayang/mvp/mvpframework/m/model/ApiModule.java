package com.jiayang.mvp.mvpframework.m.model;

import com.jiayang.mvp.mvpframework.m.service.LocationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
@Module(includes = {AppModule.class ,ClientModule.class})
public class ApiModule {


    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder(){
        return new Retrofit.Builder();
    }


    @Singleton
    @Provides
    public LocationService provideGoodsModel(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder , LocationService.BASE_URL ,client).create(LocationService.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, String baseUrl, OkHttpClient client){
        builder.client(RetrofitUrlManager.getInstance().with(client.newBuilder())
                .build())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create());
        return builder.build();
    }
}
