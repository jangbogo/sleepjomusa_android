package com.kentakang.sleepjomusa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<RecyclerItem> items = new ArrayList<>();
        RecyclerItem[] item = new RecyclerItem[3];

        item[0] = new RecyclerItem(R.drawable.bodyfirst, "잠자기전 필수 스트레칭 BEST모음", "https://www.youtube.com/watch?v=u94SQdRpHyc");
        item[1] = new RecyclerItem(R.drawable.bodysecond, "수면의 질을 높이는 전신 스트레칭 (수건 필요) | 20분 베드타임 요가 | 요가소년", "https://www.youtube.com/watch?v=coAeR-9hIM8");
        item[2] = new RecyclerItem(R.drawable.bodythird, "[무나홈트] 자기전에 하는 스트레칭 / 전신스트레칭/ stretching routine", "https://www.youtube.com/watch?v=6jkofNxoDi4");

        for (int i = 0; i < 3; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_music));
    }
}
