package com.ac.achan.dbtv.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.ac.achan.dbtv.R;

public class MenuFragment extends Fragment {
    private Listener listener;

    private ImageView closeButton;
    private ImageView menu_db;
    private ImageView menu_dbz;
    private ImageView menu_dbgt;
    private ImageView menu_dbs;

    public interface Listener {
        void onCloseClick();
        void onMenuDBClick();
        void onMenuDBZClick();
        void onMenuDBGTClick();
        void onMenuDBSClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view == null) {
            view = inflater.inflate(R.layout.menu_fragment, container, false);
        }

        closeButton = (ImageView) view.findViewById(R.id.close);
        menu_db = (ImageView) view.findViewById(R.id.menu_db);
        menu_dbz = (ImageView) view.findViewById(R.id.menu_dbz);
        menu_dbgt = (ImageView) view.findViewById(R.id.menu_dbgt);
        menu_dbs = (ImageView) view.findViewById(R.id.menu_dbs);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();

                if (listener != null) {
                    listener.onCloseClick();
                }
            }
        });

        menu_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();

                if (listener != null) {
                    listener.onMenuDBClick();
                }
            }
        });

        menu_dbz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();

                if (listener != null) {
                    listener.onMenuDBZClick();
                }
            }
        });

        menu_dbgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();

                if (listener != null) {
                    listener.onMenuDBGTClick();
                }
            }
        });

        menu_dbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();

                if (listener != null) {
                    listener.onMenuDBSClick();
                }
            }
        });

        return view;
    }

    public void setTheListener(Listener listen) {
        listener = listen;
    }

}
