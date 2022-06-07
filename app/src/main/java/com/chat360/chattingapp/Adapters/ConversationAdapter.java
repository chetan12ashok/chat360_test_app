package com.chat360.chattingapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chat360.chattingapp.Holders.ConversationHolder;
import com.chat360.chattingapp.R;

import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> implements Filterable {

    Context context;
    List<ConversationHolder> conversationHolderList;
    List<ConversationHolder> conversationHolderListAll;

    public ConversationAdapter(Context context, List<ConversationHolder> conversationHolderList) {
        this.context = context;
        this.conversationHolderList = conversationHolderList;
        this.conversationHolderListAll = new ArrayList<>(conversationHolderList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.conversation_item,parent,false);
        return new ConversationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConversationHolder conversationHolder = conversationHolderList.get(position);
        holder.name.setText(conversationHolder.getName());
        holder.massage.setText(conversationHolder.getMessage());
        holder.date.setText(conversationHolder.getTime());
        Glide.with(context).load(conversationHolder.getImageUrl()).into(holder.profile);


        if (conversationHolder.isHasStory()){
            holder.profile.setBackground(context.getResources().getDrawable(R.drawable.circular_bg));
        }

        if (conversationHolder.isHasOnline()){
            holder.online.setVisibility(View.VISIBLE);
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return conversationHolderList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter  = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<ConversationHolder> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filteredList.addAll(conversationHolderListAll);
            }else {
                for (ConversationHolder conversationHolder : conversationHolderList){
                    if (conversationHolder.getName().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(conversationHolder);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                conversationHolderList.clear();
                conversationHolderList.addAll((Collection<? extends ConversationHolder>) filterResults.values);
                notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        CircleImageView profile;
        ImageView online;
        TextView name,date,massage;
        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            online = view.findViewById(R.id.online);
            profile = view.findViewById(R.id.profile_image);
            name = view.findViewById(R.id.name);
            date = view.findViewById(R.id.date);
            massage = view.findViewById(R.id.massage);
        }
    }
}
