package com.chat360.chattingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.chat360.chattingapp.Adapters.ConversationAdapter;
import com.chat360.chattingapp.Adapters.StoryAdapter;
import com.chat360.chattingapp.Holders.ConversationHolder;
import com.chat360.chattingapp.Holders.StoryHolder;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView storyRecyclerView;
    LinearLayoutManager layoutManager;
    StoryAdapter storyAdapter;

    RecyclerView conversationRecyclerView;
    LinearLayoutManager conversationLayoutManager;
    ConversationAdapter conversationAdapter;

    List<StoryHolder> storyHolderList;
    List<ConversationHolder> conversationHolderList;

    SearchView searchView;

    ShimmerFrameLayout  top_shimmer_view_container,second_shimmer_view_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViews();

        shimmerEffect();

        getStoryData();

        getConversationData();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                conversationAdapter.getFilter().filter(s);
                return false;
            }
        });

    }

    private void findViews() {
        storyRecyclerView = findViewById(R.id.storyRecyclerView);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        storyRecyclerView.setLayoutManager(layoutManager);

        conversationRecyclerView = findViewById(R.id.conversationRecyclerView);
        conversationLayoutManager = new LinearLayoutManager(MainActivity.this);

        conversationRecyclerView.setLayoutManager(conversationLayoutManager);

        searchView = findViewById(R.id.searchView);

        storyHolderList = new ArrayList<>();
        conversationHolderList= new ArrayList<>();
    }

    private void shimmerEffect() {
        top_shimmer_view_container = findViewById(R.id.top_shimmer_view_container);
        second_shimmer_view_container = findViewById(R.id.second_shimmer_view_container);

        top_shimmer_view_container.setVisibility(View.VISIBLE);
        second_shimmer_view_container.setVisibility(View.VISIBLE);

        top_shimmer_view_container.startShimmer();
        second_shimmer_view_container.startShimmer();
    }

    private void getConversationData() {
        CollectionReference firebaseFirestore = FirebaseFirestore.getInstance().collection("Conversation");
        firebaseFirestore.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
                    ConversationHolder conversationHolder = documentSnapshot.toObject(ConversationHolder.class);
                    conversationHolderList.add(conversationHolder);
                }
                conversationAdapter = new ConversationAdapter(MainActivity.this,conversationHolderList);
                conversationRecyclerView.setAdapter(conversationAdapter);


                second_shimmer_view_container.setVisibility(View.GONE);
            }
        });

    }

    private void getStoryData() {

        CollectionReference firebaseFirestore = FirebaseFirestore.getInstance().collection("Story");
        firebaseFirestore.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
                    StoryHolder storyHolder = documentSnapshot.toObject(StoryHolder.class);
                    storyHolderList.add(storyHolder);
                }
                storyAdapter = new StoryAdapter(storyHolderList,MainActivity.this);
                storyRecyclerView.setAdapter(storyAdapter);
                top_shimmer_view_container.setVisibility(View.GONE);
            }
        });

    }
}