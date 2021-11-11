package com.huthfy.postsapp.Ui.MainActivity;


import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huthfy.postsapp.Model.Data.PostClient;
import com.huthfy.postsapp.Model.Models.PostModel;

import java.util.List;

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
        postClient.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                postsLivedata.setValue(response.body());
                Log.d(TAG, "huthfy onResponse: got data from server");
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                    failedGetPost = true;
                Log.d(TAG, "huthfy onFailure: OOPs! can't get data from server");
            }
        });
    }

    public MutableLiveData<List<PostModel>> getPostsLivedata() {
        return postsLivedata;
    }

    // get message if get posts was failed
    public String getFailedPostMSG(){
        return failedPostMSG;
    }

}
