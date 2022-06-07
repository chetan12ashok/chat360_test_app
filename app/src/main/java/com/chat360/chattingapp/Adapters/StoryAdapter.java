package com.chat360.chattingapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chat360.chattingapp.Holders.StoryHolder;
import com.chat360.chattingapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    List<StoryHolder> storyHolderList;
    Context context;

    public StoryAdapter(List<StoryHolder> storyHolderList, Context context) {
        this.storyHolderList = storyHolderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_item,parent,false);
        return new StoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            StoryHolder model = storyHolderList.get(position);
            Glide.with(context).load(model.getImageUrl()).into(holder.profile);
            holder.name.setText(model.getName());

            if (model.isHasOnline()){
                holder.online.setVisibility(View.VISIBLE);
            }

            if (model.isHasStory()){
                holder.profile.setBackground(context.getResources().getDrawable(R.drawable.circular_bg));
            }

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


    }

    @Override
    public int getItemCount() {
        return storyHolderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile;
        TextView name;
        ImageView online;
        RelativeLayout layout;
        public ViewHolder(@NonNull View view) {
            super(view);
            profile = view.findViewById(R.id.profile);
            name = view.findViewById(R.id.name);
            online = view.findViewById(R.id.online);
            layout = view.findViewById(R.id.layout);
        }
    }
}
