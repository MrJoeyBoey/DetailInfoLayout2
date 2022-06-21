package com.j.detailinfolayout2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.j.detailinfolayout2.utils.WebServiceUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DetailInfoLayout2 dil2 = findViewById(R.id.dil2);

        WebServiceUtil.PICTURE_PREFIX_URL = "https://imgs.gamersky.com/upimg/new_preview/";
        dil2.setItemBackgroundColor(Color.WHITE);
        dil2.setDetailInfo(
                new DetailInfoLayout2.Builder()
                        .add("123", "456")
                        .add("123", "456")
                        .add(0,"123", "456", DetailInfoLayout2.Flag.Next)
                        .add("123", "456")
                        .add("123", "456")
                        .add(0,"123", "456", DetailInfoLayout2.Flag.Next)
                        .add("123", "456")
                        .add("123", "456")
                        .add("123", "456")
                        .add(0,"123", "456", DetailInfoLayout2.Flag.Next)
                        .add(0, "123", "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈" +
                                "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈", DetailInfoLayout2.ValueType.RICH_TEXT)
                        .add("123", "456", new DetailInfoLayout2.TextBuilder().setTextColor(Color.BLUE))
                        .add(0, "123", "2022/06/16/origin_202206161106283671.jpg;2022/05/26/origin_202205260902455305.jpg", DetailInfoLayout2.ValueType.PICTURE, DetailInfoLayout2.Flag.Next)
                        .add(0, "123", "2022/06/10/origin_202206101234061694.jpg;2022/05/26/origin_202205260902455305.jpg", DetailInfoLayout2.ValueType.PICTURE)
                .build()
        );
    }
}