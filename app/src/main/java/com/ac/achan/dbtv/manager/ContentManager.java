package com.ac.achan.dbtv.manager;

import android.content.Context;
import android.util.Log;

import com.ac.achan.dbtv.dto.ContentDTO;
import com.ac.achan.dbtv.network.VolleySingleton;
import com.ac.achan.dbtv.utils.Constants;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public final class ContentManager {
//    private static ContentManager instance;

    private Context mCtx;

    private ContentDTO contentDTO;

    private String url;
    private int itemCounter;

    public ArrayList<HashMap<String, String>> contentPool = new ArrayList<>();

    public interface ContentListener {
        void success(JSONObject response);
        void failed(VolleyError error);
    }

    public ContentManager(Context context) {
        this.mCtx = context;
    }

    public void getContent(String token, final String searchKey, final ContentListener listener) {

        if (token == null) {
            url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + searchKey + "&type=video&key=" + Constants.YOUTUBE_API_KEY + "&maxResults=15";
        } else {
            url = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + searchKey + "&type=video&pageToken=" + token + "&key=" + Constants.YOUTUBE_API_KEY + "&maxResults=15";
        }

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        contentDTO = gson.fromJson(response.toString(), ContentDTO.class);

                        for (int i = 0; i < contentDTO.items.size(); i++) {
                            ContentDTO.Item itemsDTO = contentDTO.items.get(i);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("videoId", itemsDTO.id.videoId);
                            hashMap.put("title", itemsDTO.snippet.title);
                            hashMap.put("description", itemsDTO.snippet.description);
                            hashMap.put("publishedAt", itemsDTO.snippet.publishedAt);
                            hashMap.put("thumbnailUrl", itemsDTO.snippet.thumbnails.high.url);
                            contentPool.add(hashMap);
                            itemCounter = itemCounter + 1;
                        }

                        if (itemCounter < Constants.ITEMS_PER_SECTION) {
                            getContent(contentDTO.nextPageToken, searchKey, listener);
                        } else {
                            Log.d("OUTPUT: ", contentPool.toString());
                            if (listener != null) {
                                listener.success(response);
                            }
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.d("OUTPUT: ", "failed " + error);
                        if (listener != null) {
                            listener.failed(error);
                        }
                    }
                });
        VolleySingleton.getInstance(mCtx).addToRequestQueue(jsObjRequest);
    }
}
