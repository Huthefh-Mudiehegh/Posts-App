package com.huthfy.postsapp.Model.Data;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.huthfy.postsapp.Model.Models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private PostInterface postInterface;

    private static PostClient INSTANCE;

    private PostClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postInterface = retrofit.create(PostInterface.class);

    }

    //singleton pattern
    public static PostClient getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new PostClient();
        return INSTANCE;
    }


    //get posts from server by use api url
    public Call<List<PostModel>> getPosts(){
        Log.d(TAG, "huthfy getPosts: in client");
        return postInterface.getPosts();
    }
}
