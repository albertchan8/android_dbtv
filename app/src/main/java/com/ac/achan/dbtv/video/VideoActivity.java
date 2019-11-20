package com.ac.achan.dbtv.video;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.ac.achan.dbtv.R;
import com.ac.achan.dbtv.utils.Constants;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class VideoActivity extends FragmentActivity {
    private FrameLayout videoFrameLayout;
    private TextView title;
    private TextView description;
    private TextView like;
    private TextView comment;
    private TextView share;
    private ImageView backButton;

    protected int ePosition;
    protected String eTitle;
    protected String eVideoId;
    protected String eDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);

        videoFrameLayout = (FrameLayout) findViewById(R.id.videoFrameLayout);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        like = (TextView) findViewById(R.id.like);
        comment = (TextView) findViewById(R.id.comment);
        share = (TextView) findViewById(R.id.share);
        backButton = (ImageView) findViewById(R.id.back);

        Intent intent = getIntent();
        ePosition = intent.getIntExtra("position", 0);
        eTitle = intent.getStringExtra("title");
        eVideoId = intent.getStringExtra("videoId");
        eDescription = intent.getStringExtra("description");

        title.setText(eTitle);
        description.setText(eDescription);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "LIKE",
                        Toast.LENGTH_SHORT).show();
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "COMMENT",
                        Toast.LENGTH_SHORT).show();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "SHARE",
                        Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FragmentManager fm = getFragmentManager();
        String tag = YouTubePlayerFragment.class.getSimpleName();
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);
        if (playerFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            playerFragment = YouTubePlayerFragment.newInstance();
            ft.add(videoFrameLayout.getId(), playerFragment, tag);
            ft.commit();
        }

        playerFragment.initialize(Constants.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(eVideoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(VideoActivity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

