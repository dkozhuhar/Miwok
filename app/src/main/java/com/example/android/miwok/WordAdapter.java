package com.example.android.miwok;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> {
    private int mColourResourceId;
    private MediaPlayer mediaPlayer;

    private ArrayList<Word> mWords;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout listItemView;
        public MyViewHolder(LinearLayout view) {
            super(view);
            listItemView = view;
        }

    }
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int integer){
        LinearLayout listItemView = (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        MyViewHolder vh = new MyViewHolder(listItemView);
        LinearLayout textLayout = listItemView.findViewById(R.id.text_layout);
        int color = ContextCompat.getColor(textLayout.getContext(),mColourResourceId);
        textLayout.setBackgroundColor(color);
        return vh;
    }
    public void onBindViewHolder(MyViewHolder holder, int position){
        final Word currentWord = mWords.get(position);

        TextView defaultTranslation = holder.listItemView.findViewById(R.id.text_english);
        defaultTranslation.setText(currentWord.getDeafultTranslation());

        TextView miwokTransaltion = holder.listItemView.findViewById(R.id.text_miwok);
        miwokTransaltion.setText(currentWord.getMiwokTranslation());

        ImageView image = holder.listItemView.findViewById(R.id.image);
        if (currentWord.getImageResourceId() != 0) {
            image.setImageResource(currentWord.getImageResourceId());
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }

        holder.listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager mAudioFocus = (AudioManager) v.getContext().getSystemService(Context.AUDIO_SERVICE);
                int res
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    Log.v("WordAdapter", "Releasing mediaPlayer object");
                    Log.v("WordAdapter", "Current word: " + currentWord);
                }

                mediaPlayer = MediaPlayer.create(v.getContext(), currentWord.getMusicResourceId());

                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        Log.v("WordAdapter", "Releasing mediaPlayer object after finishing playing");
                    }
                });
            }
        });




    }
    public WordAdapter(@NonNull Context context, ArrayList<Word> words, int colourResourceId) {
        super();
        mWords = words;
        mColourResourceId = colourResourceId;

    }
    public int getItemCount(){
        return mWords.size();
    }


    public void releaseMediaPlayer(){
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
