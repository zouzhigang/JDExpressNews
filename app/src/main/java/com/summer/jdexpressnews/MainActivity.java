package com.summer.jdexpressnews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    JDExpressNewsView jd_expressnews_tip ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jd_expressnews_tip = (JDExpressNewsView) findViewById(R.id.jd_expressnews_tip);
        List<String> notices = new ArrayList<>();
        notices.add("狂欢618，购机送好礼");
        notices.add("3C嗨购 年终盛典");
        notices.add("品牌狂欢城 亿万红包嗨翻天");
        jd_expressnews_tip.addNotice(notices);
        jd_expressnews_tip.startFlipping();
        jd_expressnews_tip.setOnNoticeClickListener(new JDExpressNewsView.OnJDExpressNewsClickListener() {
            @Override
            public void onJDExpressNewsClick(int position, String notice) {
                Toast.makeText(MainActivity.this,""+notice,Toast.LENGTH_LONG).show();
            }
        });
    }
}
