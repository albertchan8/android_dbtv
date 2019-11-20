package com.ac.achan.dbtv.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ac.achan.dbtv.R;
import com.ac.achan.dbtv.manager.ContentManager;
import com.ac.achan.dbtv.utils.Constants;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * DBTV Application Created by Albert Chan
 * This app displays metadata and plays videos from the YouTube API.
 */

public class MainActivity extends FragmentActivity {
    private FrameLayout mainFrameLayout;
    private MenuFragment menuFragment;
    private LinearLayout topBar;
    private LinearLayout bottomBar;
    private ImageView menu;
    private TextView title;

    private ContentManager contentManager;

    private MainFeedFragment mainFeedFragment;

    public ArrayList<HashMap<String, String>> contentArray = new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showTopBottomBar();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mainFrameLayout = (FrameLayout) findViewById(R.id.mainFrameLayout);
        topBar = (LinearLayout) findViewById(R.id.topBar);
        bottomBar = (LinearLayout) findViewById(R.id.bottomBar);

        menu = (ImageView) findViewById(R.id.menu);
        title = (TextView) findViewById(R.id.title);

        menuFragment = new MenuFragment();
        mainFeedFragment = new MainFeedFragment();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .add(R.id.mainFrameLayout, menuFragment)
                        .addToBackStack(null)
                        .commit();
                hideTopBottomBar();
            }
        });

        menuFragment.setTheListener(new MenuFragment.Listener() {
            @Override
            public void onCloseClick() {
                showTopBottomBar();
            }

            @Override
            public void onMenuDBClick() {
                showTopBottomBar();

                contentManager = new ContentManager(getBaseContext());
                contentManager.getContent(null, Constants.SEARCH_DRAGONBALL, new ContentManager.ContentListener() {
                    @Override
                    public void success(JSONObject response) {
                        contentArray = contentManager.contentPool;

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contentArray", contentArray);
                        MainFeedFragment mainFeedFragment = new MainFeedFragment();
                        mainFeedFragment.setArguments(bundle);
                        title.setText("DRAGON BALL");
                        setReplaceFragment(mainFeedFragment);
                    }

                    @Override
                    public void failed(VolleyError error) {

                    }
                });
            }

            @Override
            public void onMenuDBZClick() {
                showTopBottomBar();

                contentManager = new ContentManager(getBaseContext());
                contentManager.getContent(null, Constants.SEARCH_DRAGONBALL_Z, new ContentManager.ContentListener() {
                    @Override
                    public void success(JSONObject response) {
                        contentArray = contentManager.contentPool;

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contentArray", contentArray);
                        MainFeedFragment mainFeedFragment = new MainFeedFragment();
                        mainFeedFragment.setArguments(bundle);
                        title.setText("DRAGON BALL Z");
                        setReplaceFragment(mainFeedFragment);
                    }

                    @Override
                    public void failed(VolleyError error) {

                    }
                });
            }

            @Override
            public void onMenuDBGTClick() {
                showTopBottomBar();

                contentManager = new ContentManager(getBaseContext());
                contentManager.getContent(null, Constants.SEARCH_DRAGONBALL_GT, new ContentManager.ContentListener() {
                    @Override
                    public void success(JSONObject response) {
                        contentArray = contentManager.contentPool;

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contentArray", contentArray);
                        MainFeedFragment mainFeedFragment = new MainFeedFragment();
                        mainFeedFragment.setArguments(bundle);
                        title.setText("DRAGON BALL GT");
                        setReplaceFragment(mainFeedFragment);
                    }

                    @Override
                    public void failed(VolleyError error) {

                    }
                });
            }

            @Override
            public void onMenuDBSClick() {
                showTopBottomBar();

                contentManager = new ContentManager(getBaseContext());
                contentManager.getContent(null, Constants.SEARCH_DRAGONBALL_SUPER, new ContentManager.ContentListener() {
                    @Override
                    public void success(JSONObject response) {
                        contentArray = contentManager.contentPool;

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contentArray", contentArray);
                        MainFeedFragment mainFeedFragment = new MainFeedFragment();
                        mainFeedFragment.setArguments(bundle);
                        title.setText("DRAGON BALL SUPER");
                        setReplaceFragment(mainFeedFragment);
                    }

                    @Override
                    public void failed(VolleyError error) {

                    }
                });
            }
        });

        contentManager = new ContentManager(getBaseContext());
        contentManager.getContent(null, Constants.SEARCH_DRAGONBALL, new ContentManager.ContentListener() {
            @Override
            public void success(JSONObject response) {
                contentArray = contentManager.contentPool;

                Bundle bundle = new Bundle();
                bundle.putSerializable("contentArray", contentArray);
                mainFeedFragment.setArguments(bundle);
                title.setText("DRAGON BALL");
                setReplaceFragment(mainFeedFragment);
            }

            @Override
            public void failed(VolleyError error) {

            }
        });
    }


    public void showTopBottomBar() {
        topBar.setVisibility(View.VISIBLE);
        bottomBar.setVisibility(View.VISIBLE);
    }

    public void hideTopBottomBar() {
        topBar.setVisibility(View.GONE);
        bottomBar.setVisibility(View.GONE);
    }

    public void setReplaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(mainFrameLayout.getId(), fragment);
//        transaction.addToBackStack("");
        if (transaction != null) {
            transaction.commit();
        }
    }
}
