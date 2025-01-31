package com.example.ex2.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.ex2.R;
import com.example.ex2.fragments.addItemFrag;
import com.example.ex2.fragments.loginFrag;
import com.example.ex2.fragments.profileFrag;
import com.example.ex2.fragments.registerFrag;
import com.example.ex2.fragments.showItemsFrag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ShoppingActivity extends AppCompatActivity {
    Fragment itemsFrag;
    Fragment addItemFrag;
    Fragment profileFrag;
    FirebaseAuth mAuth;
    ImageView profile, addItems, showItems;
    FirebaseUser currentUser;
    Button signOutBtn;
    Button addItemButton;
    String userUid;
    TextView greeting;
    FragmentContainerView fragmentContainerView;
    FirebaseFirestore db;
    DocumentReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopping_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        itemsFrag = new showItemsFrag();
        addItemFrag = new addItemFrag();
        profileFrag = new profileFrag();

        profile = findViewById(R.id.profile_section);
        addItems = findViewById(R.id.add_item_section);
        showItems = findViewById(R.id.items_section);

//        fragmentContainerView = findViewById(R.id.shoppingListContainer);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        userUid = currentUser.getUid();
        userRef = db.collection("users").document(userUid);
    }

    @Override
    public void onStart() {
        super.onStart();

        if(currentUser == null) {
            goToStartPage("Error, going back");
        }

        Map<String, Object> userData = new HashMap<>();
//
//        userRef.set(userData)
//                .addOnSuccessListener(aVoid -> {
//                    Toast.makeText(this, "Successfully put data in Firestore", Toast.LENGTH_LONG).show();
//
//                    Log.e("Firestore", "Success writing data");
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(this, "Error putting data in Firestore", Toast.LENGTH_LONG).show();
//
//                    var execption = e;
//
//                    Log.e("Firestore", "Error writing data", e);
//                });
    }


    public void goToStartPage(String message) {
        Intent intent = new Intent(this, MainActivity.class);

        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void signOut(String message) {
        mAuth.signOut();

        goToStartPage(message);
    }

    public void showProfilePage(View view) {
        showItems.setBackground(getResources().getDrawable(R.drawable.transparent));
        addItems.setBackground(getResources().getDrawable(R.drawable.transparent));
        profile.setBackground(getResources().getDrawable(R.drawable.primary_button));
        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .addToBackStack("profile_page")
                .replace(R.id.user_screen, profileFrag)
                .commit();

    }

    public void showItemsPage(View view) {
        showItems.setBackground(getResources().getDrawable(R.drawable.primary_button));
        addItems.setBackground(getResources().getDrawable(R.drawable.transparent));
        profile.setBackground(getResources().getDrawable(R.drawable.transparent));

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .addToBackStack("items_page")
                .replace(R.id.user_screen, itemsFrag)
                .commit();
    }

    public void addItemPage(View view) {
        showItems.setBackground(getResources().getDrawable(R.drawable.transparent));
        addItems.setBackground(getResources().getDrawable(R.drawable.primary_button));
        profile.setBackground(getResources().getDrawable(R.drawable.transparent));

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .addToBackStack("add_items_page")
                .replace(R.id.user_screen, addItemFrag)
                .commit();
    }

//    public void displayGreeting() {
//        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> res) {
//                if (res.isSuccessful()) {
//                    DocumentSnapshot userDoc = res.getResult();
//                    if (userDoc.exists()) {
//                        greeting.setText("Hello, " + userDoc.getString("firstName"));
//                    }
//                } else {
//                        signOut("Unauthorized");
//                }
//            }
//        });
//    }
}