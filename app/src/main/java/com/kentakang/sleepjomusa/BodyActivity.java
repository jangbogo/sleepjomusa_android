package com.kentakang.sleepjomusa;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BodyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        ImageView pictureVideo = findViewById(R.id.pictureVideo);
        ImageView pictureThumb = findViewById(R.id.pictureThumb);
        TextView textVideo = findViewById(R.id.textVideo);
        TextView textThumb = findViewById(R.id.textThumb);

        pictureVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(intent);
            }
        });

        pictureThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.post.naver.com/viewer/postView.nhn?volumeNo=10167349"));
                startActivity(intent);
            }
        });

        textVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(intent);
            }
        });
    }
}
