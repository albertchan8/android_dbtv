package com.ac.achan.dbtv.dto;

import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContentDTO extends JSONObject {
    @SerializedName("nextPageToken")
    public String nextPageToken;
    @SerializedName("items")
    public ArrayList<Item> items;

    public class Item extends JSONObject {
        @SerializedName("id")
        public Id id;
        @SerializedName("snippet")
        public Snippet snippet;
    }

    public class Id extends JSONObject {
        @SerializedName("videoId")
        public String videoId;
    }

    public class Snippet extends JSONObject {
        @SerializedName("title")
        public String title;
        @SerializedName("description")
        public String description;
        @SerializedName("publishedAt")
        public String publishedAt;
        @SerializedName("thumbnails")
        public Thumbnails thumbnails;
    }

    public class Thumbnails extends JSONObject {
        @SerializedName("high")
        public ThumbHigh high;
    }

    public class ThumbHigh extends JSONObject {
        @SerializedName("url")
        public String url;
    }
}

