package com.example.phuon.intro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterRecyclerViewYoutube extends RecyclerView.Adapter<AdapterRecyclerViewYoutube.ViewHolder> {

    ArrayList<DataRecyclerViewYoutube> arrayList;
    Context context;

    public AdapterRecyclerViewYoutube(ArrayList<DataRecyclerViewYoutube> arrayListYoutube, Context context){
        this.arrayList = arrayListYoutube;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPlaylist;
        TextView textViewTitle;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageViewPlaylist = (ImageView) itemView.findViewById(R.id.imageViewKind);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewBXH);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, PlayListYoutube.class);
//                    view.getContext().startActivity(intent);
//                }
//            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_youtube_recycleview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.get().load(arrayList.get(position).getImageIcon()).into(holder.imageViewPlaylist);
        holder.textViewTitle.setText(arrayList.get(position).getNameList());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlayListYoutube.class);
                intent.putExtra("IDListYoutube", arrayList.get(position).getKeyListYoutube());
                intent.putExtra("title", arrayList.get(position).getNameList());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
