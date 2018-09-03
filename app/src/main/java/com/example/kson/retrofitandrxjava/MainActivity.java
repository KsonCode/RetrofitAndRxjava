package com.example.kson.retrofitandrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kson.retrofitandrxjava.api.UserApi;
import com.example.kson.retrofitandrxjava.common.Constants;
import com.example.kson.retrofitandrxjava.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login(View view) {
        //
        Map<String, String> params = new HashMap<>();
        params.put("mobile", "18612991023");
        params.put("password", "222222");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        //第一步创建对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)//初始化base——url
                .client(okHttpClient)//关联okhttp
                .addConverterFactory(GsonConverterFactory.create())//添加数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//请求回调适配器，作用，接口返回的对象的转换,比如把转换成rxjava的observable对象
                .build();

        //第二步，初始化API类
        UserApi userApi = retrofit.create(UserApi.class);

//        userApi.login2(params).enqueue(new Callback<UserEntity>() {
//            @Override
//            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
//
//                UserEntity userEntity = response.body();
//                Toast.makeText(MainActivity.this, ""+userEntity.msg, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<UserEntity> call, Throwable t) {
//
//            }
//        });

        Observable<UserEntity> userEntityObservable = userApi.login3(params);//返回rxjava的被观察者

//        userApi.login3(params).subscribe(new Observer<UserEntity>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(UserEntity userEntity) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        //.subcribeon方法的作用：🈯定发射器所在的线程，也就是请求数据所在的线程
        //.observeOn响应的线程，在哪个线程
        userApi.login3(params).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity userEntity) throws Exception {
                //主线程
                System.out.println("userEntity:" + userEntity.msg);
                Toast.makeText(MainActivity.this, "" + userEntity.msg, Toast.LENGTH_SHORT).show();
//

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

    }
}
