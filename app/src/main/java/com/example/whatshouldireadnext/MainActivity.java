package com.example.whatshouldireadnext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://stackoverflow.com/questions/6343166/how-to-fix-android-os-networkonmainthreadexception
        String webPage = "https://www.google.ie";

        String html = "not null";
       try {
           html = Jsoup.connect(webPage).get().html();
       } catch (IOException e) {
           html = String.valueOf(e);
        }

        Log.i("Console", html);
    }

}