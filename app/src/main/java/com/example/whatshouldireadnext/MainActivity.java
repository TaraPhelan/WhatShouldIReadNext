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
            int firstTitleIndex = html.indexOf("<title>");
            Log.i("Console", "First occurrence of <title>"+
                    " is found at : " + firstTitleIndex);
            int secondTitleIndex = html.indexOf("</title>");
            Log.i("Console", "First occurrence of </title>"+
                    " is found at : " + secondTitleIndex);
            String title = html.substring(firstTitleIndex+7, secondTitleIndex);
            Log.i("Console", title);

            //This creates a new string to contain the book's author
            int firstAuthorIndex = html.indexOf("<name>");
            Log.i("Console", "First occurrence of <name>"+
                    " is found at : " + firstAuthorIndex);
            int secondAuthorIndex = html.indexOf("</name>");
            Log.i("Console", "First occurrence of </name>"+
                    " is found at : " + secondAuthorIndex);
            String author = html.substring(firstAuthorIndex+14, secondAuthorIndex);
            Log.i("Console", author);

            //This creates a new string to contain the book's link
            int firstLinkIndex = html.indexOf("<link>");
            Log.i("Console", "First occurrence of <link>"+
                    " is found at : " + firstLinkIndex);
            int secondLinkIndex = html.indexOf("</link>");
            Log.i("Console", "First occurrence of </link>"+
                    " is found at : " + secondLinkIndex);
            String link = html.substring(firstLinkIndex+11, secondLinkIndex);
            Log.i("Console", link);


            Log.i("Console", html);
            return null;
        }
    }

}