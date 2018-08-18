package com.kentakang.sleepjomusa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        ImageView imageMusic = findViewById(R.id.pictureMusic);
        ImageView imageBody = findViewById(R.id.pictureBody);
        TextView textMusic = findViewById(R.id.textMusic);
        TextView textBody = findViewById(R.id.textBody);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.actionDashboard:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.actionLight:
                        Intent intent1 = new Intent(getApplicationContext(), LightActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.actionTip:
                        Intent intent2 = new Intent(getApplicationContext(), TipActivity.class);
                        startActivity(intent2);
                        break;
                }

                return true;
            }
        });

        imageMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), MusicActivity.class);
                startActivity(intent4);
            }
        });

        imageBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), BodyActivity.class);
                startActivity(intent3);
            }
        });

        textMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), MusicActivity.class);
                startActivity(intent4);
            }
        });

        textBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), BodyActivity.class);
                startActivity(intent3);
            }
        });
    }
}
