package com.huthfy.postsapp.Ui.MainActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huthfy.postsapp.Model.Models.PostModel;
import com.huthfy.postsapp.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    ArrayList<PostModel> arrayList;
    public PostAdapter(){
        arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostModel post = arrayList.get(position);
        holder.title.setText(post.getTitle());
        holder.id.setText(post.getId()+"");
        holder.body.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    // set post to arraylist
    public void setArrayList(ArrayList<PostModel> arrayList){
        this.arrayList = arrayList;
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        TextView title, id, body;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.postTitleTV);
            id= itemView.findViewById(R.id.postIdTV);
            body = itemView.findViewById(R.id.postBodyTV);

        }
    }
}
