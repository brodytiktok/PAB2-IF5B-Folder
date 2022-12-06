package com.if5b.myapplication.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if5b.myapplication.Models.Post;
import com.if5b.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {
    private List<com.if5b.myapplication.Models.Post>data = new ArrayList<>();

    public void setData(List<com.if5b.myapplication.Models.Post>data) {
        this.data = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = data.get(position);
        holder.tvUsername.setText(post.getUsername());
        holder.tvContent.setText(post.getContent());
        holder.tvDate.setText(post.getCreateDate());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUsername;
        public ViewHolder(@NonNull View itemView) {
            tvUsername = itemView.findViewById(R.id.tv_username);
            super(itemView);
        }
    }
}
