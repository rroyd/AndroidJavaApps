package com.example.ex2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.ex2.R;
import com.example.ex2.fragments.loginFrag;
import com.example.ex2.fragments.registerFrag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    public static FirebaseUser loggedUser;
    Fragment loginFragment;
    Fragment registerFragment;

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

        loginFragment = new loginFrag();
        registerFragment = new registerFrag();

        mAuth = FirebaseAuth.getInstance();
    }

    public void goToRegister(View view) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("register_fragment")
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, registerFragment).commit();
    }

    public void goToLogin(View view) {
        getSupportFragmentManager().beginTransaction()
                .addToBackStack("login_fragment")
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, loginFragment).commit();
    }

    public void register(View view) {
        String firstName = registerFrag.getRegisteredFirstName();
        String lastName = registerFrag.getRegisteredLastName();
        String email = registerFrag.getRegisteredEmail();
        String password = registerFrag.getRegisteredPassword();

        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(MainActivity.this, "Missing credentials. Please fill all inputs.", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Welcome to my shopping list!", Toast.LENGTH_LONG).show();

                            loggedUser = mAuth.getCurrentUser();

                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            Map<String, Object> userData = new HashMap<>();
                            userData.put("firstName", firstName);
                            userData.put("lastName", lastName);
                            DocumentReference docRef = db.document("users");

                            docRef.set(userData);

                            goToShoppingList();
                        } else {
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                var exception = task.getException();

                                Log.d("ERROR", "createUserWithEmail:failure" + task.getException());
                        }
                    }
                });
    }

    public void login(View view) {
        String email = loginFrag.getLoggedInEmail();
        String password = loginFrag.getLoggedInPassword();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Welcome back!", Toast.LENGTH_LONG).show();

                            goToShoppingList();
                        } else {
                            Toast.makeText(MainActivity.this, "User not found. Please register!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void goToShoppingList() {
        Intent intent = new Intent(MainActivity.this, ShoppingActivity.class);

        startActivity(intent);
    }

}