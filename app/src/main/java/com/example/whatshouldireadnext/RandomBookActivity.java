package com.example.whatshouldireadnext;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;

import java.io.IOException;

public class RandomBookActivity extends AppCompatActivity {
    private static final String TAG = "RandomBookActivity";

    // Sets up class-wide variables
    TextView titleTextView, authorTextView, linkTextView;
    String title, author, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_book);

        // Sets up UI elements to be used by Java
        titleTextView = findViewById(R.id.text_title);
        authorTextView = findViewById(R.id.text_author);
        linkTextView = findViewById(R.id.text_link);

        ExampleRunnable exampleRunnable = new ExampleRunnable();
        new Thread(exampleRunnable).start();
    }

    class ExampleRunnable implements Runnable {

        ExampleRunnable() {
            // Constructor
        }

        @Override
        public void run() {

            // Creates a String with details of the random book returned from Goodreads
            // Link modified from https://medium.com/@kjbrazil/goodreads-random-next-book-selection-f6c6b325b273
            String webPage = "https://www.goodreads.com/review/list?v=2&key=FqPAOyWHR78g8gQjLHOg&id=46330375&shelf=to-read&sort=random&per_page=1";
            String html;
            try {
                html = Jsoup.connect(webPage).get().html();
            } catch (IOException e) {
                html = String.valueOf(e);
            }

            // Saves the book's title to a String
            int firstTitleIndex = html.indexOf("<title>");
            Log.i(TAG, "First occurrence of <title>" +
                    " is found at : " + firstTitleIndex);
            int secondTitleIndex = html.indexOf("</title>");
            Log.i(TAG, "First occurrence of </title>" +
                    " is found at : " + secondTitleIndex);
            title = html.substring(firstTitleIndex + 7, secondTitleIndex);
            Log.i(TAG, title);

            // Saves the book's author to a String
            int firstAuthorIndex = html.indexOf("<name>");
            Log.i(TAG, "First occurrence of <name>" +
                    " is found at : " + firstAuthorIndex);
            int secondAuthorIndex = html.indexOf("</name>");
            Log.i(TAG, "First occurrence of </name>" +
                    " is found at : " + secondAuthorIndex);
            author = html.substring(firstAuthorIndex + 14, secondAuthorIndex);
            Log.i(TAG, author);

            // Saves the book's link to a String
            int firstLinkIndex = html.indexOf("<link>");
            Log.i(TAG, "First occurrence of <link>" +
                    " is found at : " + firstLinkIndex);
            int secondLinkIndex = html.indexOf("</link>");
            Log.i(TAG, "First occurrence of </link>" +
                    " is found at : " + secondLinkIndex);
            link = html.substring(firstLinkIndex + 11, secondLinkIndex);
            Log.i(TAG, link);

            // Prints all the returned HTML to the console
            Log.i(TAG, html);

            // Updates TextViews with random book info
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    titleTextView.setText(title);
                    authorTextView.setText(author);
                    linkTextView.setText(link);
                }
            });
        }
    }
}