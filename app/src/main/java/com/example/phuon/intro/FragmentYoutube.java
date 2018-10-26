package com.example.phuon.intro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class FragmentYoutube extends Fragment {
    View view;

    public static String KEY_API_YOUTUBE = "AIzaSyBJS58MUw2o3Pj-Z4Fru5ctSB_ZrwBcWtE";
    private String ID_CHANEL_YOUTUBE = "UC2K22g4Nxzgn4UVjMpYWPgg";
    private String URL_PLAYLIST_YOUTUBE = "https://www.googleapis.com/youtube/v3/playlists/?maxResults=50&channelId=" +
           ID_CHANEL_YOUTUBE +"&part=snippet&key="+KEY_API_YOUTUBE;

    AdapterRecyclerViewYoutube adapterRecyclerViewYoutube;
    ArrayList<DataRecyclerViewYoutube> arrayList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_youtube, container, false);
        initRecyclerView();
        view.setTag("phuong");
        return view;
    }

    @Override
    public void onDestroyView() {
        arrayList.clear();
        adapterRecyclerViewYoutube.notifyDataSetChanged();
        super.onDestroyView();
    }

    public void initRecyclerView(){
        RecyclerView recyclerView = view.findViewById(R.id.recycleViewYoutube);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapterRecyclerViewYoutube = new AdapterRecyclerViewYoutube(arrayList, view.getContext().getApplicationContext());
        recyclerView.setAdapter(adapterRecyclerViewYoutube);

        final RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_PLAYLIST_YOUTUBE, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String IDPlayList = "";
                        String titelPlaylist = "";
                        String thumbnailsImg = "";
                        JSONArray jsonArrayItems = response.getJSONArray("items");
                        for (int i = 0; i < jsonArrayItems.length(); i++){
                            JSONObject jsonObjectItems = jsonArrayItems.getJSONObject(i);
                            IDPlayList = jsonObjectItems.getString("id");
                            JSONObject jsonObjectSnippet = jsonObjectItems.getJSONObject("snippet");
                            titelPlaylist = jsonObjectSnippet.getString("title");
                            JSONObject jsonObjectThumbnails = jsonObjectSnippet.getJSONObject("thumbnails");
                            JSONObject jsonObjectMedium = jsonObjectThumbnails.getJSONObject("medium");
                            thumbnailsImg = jsonObjectMedium.getString("url");
                            arrayList.add(new DataRecyclerViewYoutube(thumbnailsImg, titelPlaylist, IDPlayList));
                        }
                        adapterRecyclerViewYoutube.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
