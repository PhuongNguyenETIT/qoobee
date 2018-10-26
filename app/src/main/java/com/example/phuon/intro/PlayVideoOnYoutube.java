package com.example.phuon.intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class PlayVideoOnYoutube extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer player;
    String id = "";
    private int REQUEST_VIDEO = 45;
    ArrayList<DetailListVideoYoutube> youtubeArrayList;
    VideoYoutubeAdapter youtubeAdapter;
    ListView listViewShow;
    ArrayList<DetailListVideoYoutube> arrayListSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_on_youtube);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlayer);
        youTubePlayerView.initialize(FragmentYoutube.KEY_API_YOUTUBE, this);

        Intent intent = getIntent();
        id = intent.getStringExtra("IDVideo");
        int number = intent.getIntExtra("number", 0);

        listViewShow = (ListView) findViewById(R.id.listViewShowPlay);
        youtubeArrayList = new ArrayList<>();
        youtubeArrayList = (ArrayList<DetailListVideoYoutube>) intent.getSerializableExtra("arrayListVideo");
        arrayListSave = new ArrayList<>();
        arrayListSave.add(youtubeArrayList.get(number));
        youtubeArrayList.remove(number);
        youtubeAdapter = new VideoYoutubeAdapter(this, R.layout.list_video_youtube, youtubeArrayList);
        listViewShow.setAdapter(youtubeAdapter);

        listViewShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                player.loadVideo(youtubeArrayList.get(i).getIDVideo());
                youtubeArrayList.add(youtubeArrayList.size(), arrayListSave.get(0));
                arrayListSave.remove(0);
                arrayListSave.add(youtubeArrayList.get(i));
                youtubeArrayList.remove(i);
                youtubeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b) {
            youTubePlayer.loadVideo(id);
        }
        player = youTubePlayer;
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(PlayVideoOnYoutube.this, REQUEST_VIDEO);
        }
        else {
            Toast.makeText(PlayVideoOnYoutube.this, "Kiểm tra kết nối Internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_VIDEO){
            youTubePlayerView.initialize(FragmentYoutube.KEY_API_YOUTUBE, PlayVideoOnYoutube.this);
        }
    }
}
