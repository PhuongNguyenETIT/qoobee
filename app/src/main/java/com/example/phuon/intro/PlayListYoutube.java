package com.example.phuon.intro;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class PlayListYoutube extends AppCompatActivity {

    ListView listViewVideo;
    ArrayList<DetailListVideoYoutube> youtubeArrayList;
    VideoYoutubeAdapter youtubeAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list_youtube);


        toolbar = (Toolbar) findViewById(R.id.toolbarPlayYoutube);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayListYoutube.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String ID_LIST_YOUTUBE = intent.getStringExtra("IDListYoutube");
        String title = intent.getStringExtra("title");
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        String getJSONYoutube = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ ID_LIST_YOUTUBE
                +"&key=" + FragmentYoutube.KEY_API_YOUTUBE +"&maxResults=50";

        listViewVideo = (ListView) findViewById(R.id.listViewYoutube);
        youtubeArrayList = new ArrayList<>();
        youtubeAdapter = new VideoYoutubeAdapter(this, R.layout.list_video_youtube, youtubeArrayList);
        listViewVideo.setAdapter(youtubeAdapter);

        GetJsonPlayList(getJSONYoutube);

        listViewVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PlayListYoutube.this, PlayVideoOnYoutube.class);
                intent.putExtra("IDVideo", youtubeArrayList.get(i).getIDVideo());
                intent.putExtra("number", i);
                intent.putExtra("arrayListVideo", youtubeArrayList);
                startActivity(intent);
            }
        });
    }

    private void GetJsonPlayList(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String title = "";
                        String urlThumbnails = "";
                        String idVideo = "";
                        JSONArray jsonArrayItems = response.getJSONArray("items");
                        for (int i = 0; i < jsonArrayItems.length(); i++){
                            JSONObject jsonObjectItems = jsonArrayItems.getJSONObject(i);
                            JSONObject jsonObjectSnippet = jsonObjectItems.getJSONObject("snippet");
                            title = jsonObjectSnippet.getString("title");
                            JSONObject jsonObjectThumbnails =jsonObjectSnippet.getJSONObject("thumbnails");
                            JSONObject jsonObjectMedium = jsonObjectThumbnails.getJSONObject("medium");
                            urlThumbnails = jsonObjectMedium.getString("url");
                            JSONObject jsonObjectResourceId = jsonObjectSnippet.getJSONObject("resourceId");
                            idVideo = jsonObjectResourceId.getString("videoId");
                            youtubeArrayList.add(new DetailListVideoYoutube(title, urlThumbnails, idVideo));
                        }
                        youtubeAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PlayListYoutube.this, "Error", Toast.LENGTH_SHORT).show();
            }
         }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
