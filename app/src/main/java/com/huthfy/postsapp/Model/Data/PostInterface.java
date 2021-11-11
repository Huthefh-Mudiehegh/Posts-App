package com.huthfy.postsapp.Model.Data;

import com.huthfy.postsapp.Model.Models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {

    @GET("posts")
    Call<List<PostModel>> getPosts();

}
