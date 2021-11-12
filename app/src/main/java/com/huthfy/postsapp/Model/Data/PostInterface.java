package com.huthfy.postsapp.Model.Data;

import com.huthfy.postsapp.Model.Models.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {

    @GET("posts")
    Observable<List<PostModel>> getPosts();

}
