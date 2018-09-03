package com.example.kson.retrofitandrxjava;

import com.example.kson.retrofitandrxjava.entity.UserEntity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2018/09/03
 * Description:
 */
public class Test {
    public static void main(String[] args){

        //被观察者,创建操作符，
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            /**
             * 被观察者的回调方法，参数emitter是发射器，发射事件的发射器
             */
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onNext(1);//发送事件的方法
                emitter.onNext(2);
//                emitter.onError(new Throwable());
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onComplete();//我发送结束了
            }
        });

        //创建观察者对象
        Observer<Integer> observer = new Observer<Integer>(){

            @Override
            public void onSubscribe(Disposable d) {

                d.isDisposed();

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);

            }

            /**
             * 有异常的时候回调的方法
             * @param e
             */
            @Override
            public void onError(Throwable e) {



            }

            /**
             * 发送事件结束的时候
             */
            @Override
            public void onComplete() {

            }
        };



        //订阅
        observable.subscribe(observer);



        //链式调用1
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {


            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        //链式调用2
        Observable.create(new ObservableOnSubscribe<UserEntity>() {

            @Override
            public void subscribe(ObservableEmitter<UserEntity> emitter) throws Exception {

            }
        }).subscribe(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity userEntity) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

    }
}
