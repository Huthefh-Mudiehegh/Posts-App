package com.huthfy.postsapp.Ui.MainActivity;


import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.postsapp.Model.Data.PostClient;
import com.huthfy.postsapp.Model.Models.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    //fields if bringPosts method get failure
    public boolean failedGetPost = false;
    private String failedPostMSG = "OPPS! failed to get post from server";


    // posts list livedata
    private MutableLiveData<List<PostModel>> postsLivedata = new MutableLiveData<>();

    // class used to reach the server
    PostClient postClient;

    // constructor
    public PostViewModel(){
        postClient = PostClient.getINSTANCE();
    }


    //bring posts from server
    public void bringPosts(){
        Observable observable = postClient.getPosts()
                .subscribeOn(Schedulers.io());

        // note: be sure to choose observer from "reactivex"
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(io.reactivex.disposables.Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                postsLivedata.postValue((List<PostModel>) value);
                Log.d(TAG, "huthfy onResponse: got data from server");

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "huthfy Oops! failed to get data from server");

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public MutableLiveData<List<PostModel>> getPostsLivedata() {
        return postsLivedata;
    }

    // get message if get posts was failed
    public String getFailedPostMSG(){
        return failedPostMSG;
    }

}
