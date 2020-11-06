package com.example.whatshouldireadnext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LongRunningTask().execute( );
    }

    //This Async Task is used because networking activity can not take place on the main activity thread for performance reasons
    private static class LongRunningTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //this creates a string with details of the random book returned from the GoodReads link
            //link modified from https://medium.com/@kjbrazil/goodreads-random-next-book-selection-f6c6b325b273
            String webPage = "https://www.goodreads.com/review/list?v=2&key=FqPAOyWHR78g8gQjLHOg&id=46330375&shelf=to-read&sort=random&per_page=1";
            String html;
            try {
                html = Jsoup.connect(webPage).get().html();
            } catch (IOException e) {
                html = String.valueOf(e);
            }

            //This creates a new string to contain the book's title
            int firstIndex = html.indexOf("<title>");
            Log.i("Console", "First occurrence of <title>"+
                    " is found at : " + firstIndex);
            int secondIndex = html.indexOf("</title>");
            Log.i("Console", "First occurrence of </title>"+
                    " is found at : " + secondIndex);
            Log.i("Console", html.substring(firstIndex+7, secondIndex));


            Log.i("Console", html);
            return null;
        }
    }

}