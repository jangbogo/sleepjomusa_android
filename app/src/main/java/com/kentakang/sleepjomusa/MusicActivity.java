package com.kentakang.sleepjomusa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<RecyclerItem> items = new ArrayList<>();
        RecyclerItem[] item = new RecyclerItem[5];

        item[0] = new RecyclerItem(R.drawable.first, "깊은 수면/안정을 위한 클래식 \uD83D\uDC11 Classical Music for Sleeping, Restingㅣ잠잘때 듣는 음악", "https://www.youtube.com/watch?v=SXulYA6-7cc");
        item[1] = new RecyclerItem(R.drawable.second, "\"꿀잠용\" 잠에 빠져들게 하는 클래식 classic32", "https://www.youtube.com/watch?v=Hq0t13VQgUM");
        item[2] = new RecyclerItem(R.drawable.third, "(6 HOURS) 잔잔한 수면유도 힐링음악/클래식 \uD83D\uDC11 Classical Music for Sleeping, Relaxingㅣ수면, 숙면, 스트레스 해소", "https://www.youtube.com/watch?v=egOjwzErHi4");
        item[3] = new RecyclerItem(R.drawable.fourth, "조용한 수면유도 클래식음악 연속듣기", "https://www.youtube.com/watch?v=_f8k2-kFklY");
        item[4] = new RecyclerItem(R.drawable.five, "마음이 편해지는 클래식명곡 연속듣기", "https://www.youtube.com/watch?v=0izQMAJc_S8");

        for (int i = 0; i < 5; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(), items, R.layout.activity_music));
    }
}
