package com.example.tvshowapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowapp.R;
import com.example.tvshowapp.models.Character;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private final Context context;
    private ArrayList<Character> characters;

    public RecyclerAdapter(Context context, ArrayList<Character> characters) {
        this.context = context;
        this.characters = characters;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView characterTv;
        private ImageView characterImageView;

        public MyViewHolder(final View view) {
            super(view);

            characterTv = view.findViewById(R.id.characterName);
            characterImageView = view.findViewById(R.id.characterImage);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View characterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent, false);
        return new MyViewHolder(characterView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String characterName = characters.get(position).getCharacterName();
        holder.characterTv.setText(characterName);

        Integer characterImage = characters.get(position).getImage();
        holder.characterImageView.setImageResource(characterImage);

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
