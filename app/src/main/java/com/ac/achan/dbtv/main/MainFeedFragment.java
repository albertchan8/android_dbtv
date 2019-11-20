package com.ac.achan.dbtv.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ac.achan.dbtv.R;
import com.ac.achan.dbtv.video.VideoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFeedFragment extends Fragment {
    private RecyclerView feedRecyclerView;

    private MainFeedAdapter mainFeedAdapter;

    ArrayList<HashMap<String,String>> contentArray = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.main_feed_fragment, container, false);
        }

        Bundle bundle = this.getArguments();
        if (bundle.getSerializable("contentArray") != null) {
            contentArray = (ArrayList<HashMap<String, String>>) bundle.getSerializable("contentArray");
            Log.d("MainFeedFragment: ", contentArray.toString());
        }

        feedRecyclerView = (RecyclerView) view.findViewById(R.id.feedRecyclerView);

        feedRecyclerView.setHasFixedSize(true);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        feedRecyclerView.setLayoutManager(linearLayoutManager);

        mainFeedAdapter = new MainFeedAdapter(getContext(), contentArray);
        feedRecyclerView.setAdapter(mainFeedAdapter);

        mainFeedAdapter.setAdapterListener(new MainFeedAdapter.AdapterListener() {
            @Override
            public void onItemClick(int position, String title, String date, String videoId, String description) {
                Intent intent = new Intent();
                intent.putExtra("position", position);
                intent.putExtra("title", title);
                intent.putExtra("date", date);
                intent.putExtra("videoId", videoId);
                intent.putExtra("description", description);

                intent.setClass(getContext(), VideoActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}