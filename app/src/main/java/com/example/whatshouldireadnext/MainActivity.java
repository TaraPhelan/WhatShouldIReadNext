package com.example.whatshouldireadnext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finds UI elements to be used by Java
        Button randomBookButton = findViewById(R.id.button_random_book);
        final Button goodreadsUserIdButton = findViewById(R.id.button_goodreads_user_id);
        final EditText goodreadsUserIdEditText = findViewById(R.id.editTextNumber_goodreads_user_id);
        final Button saveButton = findViewById(R.id.button_save);

        // Hides UI elements needed to update Goodreads user ID
        goodreadsUserIdEditText.setVisibility(GONE);
        saveButton.setVisibility(GONE);

        // Opens RandomBookActivity when randomBookButton is pressed
        randomBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startRandomBookActivity = new Intent(getApplicationContext(), RandomBookActivity.class);
                startActivity(startRandomBookActivity);
            }
        });

        // Shows EditText and Save button to allow a user to enter a Goodreads user ID
        goodreadsUserIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodreadsUserIdButton.setVisibility(GONE);
                goodreadsUserIdEditText.setVisibility(View.VISIBLE);
                saveButton.setVisibility(View.VISIBLE);
            }
        });
    }
}