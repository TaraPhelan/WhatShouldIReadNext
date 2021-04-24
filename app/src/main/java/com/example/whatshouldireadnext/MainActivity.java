package com.example.whatshouldireadnext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finds UI elements to be used by Java
        Button randomBookButton = findViewById(R.id.button_random_book);

        // Opens RandomBookActivity when randomBookButton is pressed
        randomBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startRandomBookActivity = new Intent(getApplicationContext(), RandomBookActivity.class);
                startActivity(startRandomBookActivity);
            }
        });
    }
}