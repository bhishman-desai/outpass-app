package com.example.homedashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.Objects;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<String> sList;
    public static int x;
    public static CardView cardView1, cardView2, cardView3, cardView4, cardView5;
    public ConnectivityManager manager;
    public NetworkInfo activeNetwork;
    ImageView slider, loading;
    //Declaration
    private DatabaseReference mDatabase;
    private AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        //Initialization
        x = 0;
        cardView1 = findViewById(R.id.cardView1);
        cardView2 = findViewById(R.id.cardView2);
        cardView3 = findViewById(R.id.cardView3);
        cardView4 = findViewById(R.id.cardView4);
        cardView5 = findViewById(R.id.cardView5);

        loading = findViewById(R.id.imageView2);
        animation = (AnimationDrawable) loading.getDrawable();
        loading.setVisibility(View.INVISIBLE);

        sList = new ArrayList<>();

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        cardView5.setOnClickListener(this);

        slider = findViewById(R.id.slider);

        Animation down_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        slider.setAnimation(down_animation);
    }

    @Override
    public void onClick(View v) {
        manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork) {
            switch (v.getId()) {
                case R.id.cardView1:
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("1");
                    convertMethod();
                    avoidDoubleClick();
                    x = 1;
                    break;
                case R.id.cardView2:
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("2");
                    convertMethod();
                    avoidDoubleClick();
                    x = 2;
                    break;
                case R.id.cardView3:
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("3");
                    convertMethod();
                    avoidDoubleClick();
                    x = 3;
                    break;
                case R.id.cardView4:
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("4");
                    convertMethod();
                    avoidDoubleClick();
                    x = 4;
                    break;
                case R.id.cardView5:
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("5");
                    convertMethod();
                    avoidDoubleClick();
                    x = 5;
                    break;
                default:
                    break;

            }
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cardView1.setEnabled(true);
        cardView2.setEnabled(true);
        cardView3.setEnabled(true);
        cardView4.setEnabled(true);
        cardView5.setEnabled(true);
    }

    private void avoidDoubleClick() {
        loading.setVisibility(View.VISIBLE);
        animation.start();

        cardView1.setEnabled(false);
        cardView2.setEnabled(false);
        cardView3.setEnabled(false);
        cardView4.setEnabled(false);
        cardView5.setEnabled(false);

    }

    private void newActivity() {
        Intent intent = new Intent(Dashboard.this, CalenderPage.class);
        startActivity(intent);
        loading.setVisibility(View.INVISIBLE);
        animation.stop();
    }

    private void convertMethod() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sList.clear();
                for (DataSnapshot next : dataSnapshot.getChildren()) {
                    sList.add(Objects.requireNonNull(next.getValue()).toString());
                }
                newActivity();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
