package com.example.tvshowapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Grid;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshowapp.adapters.RecyclerAdapter;
import com.example.tvshowapp.models.Character;

import com.example.tvshowapp.RecyclerItemClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Character> characters;
    private RecyclerView recyclerView;

    Dialog dialog;
    Button closeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.mRV);

        characters = new ArrayList<Character>(10);

        setUpCharacters();

        setCharacterAdapter();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        showDialog(view, position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        showDialog(view, position);
                    }
                }));

    }

    private void setCharacterAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(this,characters);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    private void setUpCharacters() {
        String[] characterNames = getResources().getStringArray(R.array.list_of_characters);
        String[] characterDescriptions = getResources().getStringArray(R.array.character_descriptions);
        ArrayList<Integer> characterImages = new ArrayList<Integer>();

        characterImages.add(R.drawable.spongbob);
        characterImages.add(R.drawable.patrick);
        characterImages.add(R.drawable.squidward);
        characterImages.add(R.drawable.mr_krabs);
        characterImages.add(R.drawable.gary);
        characterImages.add(R.drawable.plankton);
        characterImages.add(R.drawable.sandy);
        characterImages.add(R.drawable.mrs_puff);
        characterImages.add(R.drawable.pearl);
        characterImages.add(R.drawable.karen);

        for(int i  = 0; i<10;i++) {
            characters.add(new Character(characterNames[i], characterDescriptions[i], characterImages.get(i)));
        }
    }

    public void showDialog(View view, int position) {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(true);
        dialog.setContentView(R.layout.character_description_popup);
        dialog.show();

        TextView characterTitle = dialog.findViewById(R.id.characterTitle);
        TextView characterDescription = dialog.findViewById(R.id.characterDescription);
        closeButton = dialog.findViewById(R.id.close_button);
        ImageView characterImage = dialog.findViewById(R.id.characterImage);

        characterTitle.setText(characters.get(position).getCharacterName());
        characterDescription.setText(characters.get(position).getCharacterDescription());
        characterImage.setImageResource(characters.get(position).getImage());

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}