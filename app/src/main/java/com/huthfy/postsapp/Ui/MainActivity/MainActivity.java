package com.huthfy.postsapp.Ui.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.huthfy.postsapp.Model.Models.PostModel;
import com.huthfy.postsapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PostViewModel postViewModel;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postViewModel =new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.bringPosts();
        if (!postViewModel.failedGetPost) {
            //PostAdapter
            postAdapter = new PostAdapter();

            // recyclerView
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(postAdapter);

            postViewModel.getPostsLivedata().observe(this, new Observer<List<PostModel>>() {
                @Override
                public void onChanged(List<PostModel> postModels) {
                    postAdapter.setArrayList((ArrayList<PostModel>) postModels);
                    postAdapter.notifyDataSetChanged();
                }
            });
        }
        else
            Toast.makeText(this, postViewModel.getFailedPostMSG(), Toast.LENGTH_SHORT).show();
    }
}