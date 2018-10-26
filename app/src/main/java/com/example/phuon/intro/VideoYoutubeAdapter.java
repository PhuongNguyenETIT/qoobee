package com.example.phuon.intro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoYoutubeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DetailListVideoYoutube> detailListVideoYoutubes;

    public VideoYoutubeAdapter(Context context, int layout, List<DetailListVideoYoutube> detailListVideoYoutubes) {
        this.context = context;
        this.layout = layout;
        this.detailListVideoYoutubes = detailListVideoYoutubes;
    }

    @Override
    public int getCount() {
        return detailListVideoYoutubes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class Holder {
        ImageView imageViewVideo;
        TextView textViewTitle;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if(view == null){
            holder = new Holder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            holder.imageViewVideo = (ImageView) view.findViewById(R.id.imageVideo);
            holder.textViewTitle =(TextView) view.findViewById(R.id.textTitle);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        DetailListVideoYoutube listVideoYoutube = detailListVideoYoutubes.get(i);
        holder.textViewTitle.setText(listVideoYoutube.getTitle());
        Picasso.get().load(listVideoYoutube.getThumbnails()).into(holder.imageViewVideo);

        return view;
    }
}
