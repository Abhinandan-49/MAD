package com.techiguru.voiceguru;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageView iv_speak;
    int processID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        iv_speak = findViewById(R.id.iv_speak);
        // to click on the speak image
        iv_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TO recognize the voice
                Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                // to get all languages
                voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                // TO GET select language from device
                voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                // to show message for speech
                voice.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now");
                // pass the intent to os
                try {
                    startActivityForResult(voice, processID);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Speech recognition not supported", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // to get the os output to text format

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == processID && data != null) {
            // to get the text from intent
            ArrayList<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (list == null || list.isEmpty()) {
                return;
            }
            String command = list.get(0).toLowerCase();
            Toast.makeText(this, command, Toast.LENGTH_SHORT).show();
            // to pass the no of operations based on statement
            try {
                switch (command) {
                    case "open camera":
                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(camera);
                        break;

                    case "play song":
                    case "play music":
                    case "open music":
                        Intent music = new Intent(Intent.ACTION_MAIN);
                        music.addCategory(Intent.CATEGORY_APP_MUSIC);
                        startActivity(music);
                        break;

                    case "open youtube":
                        Intent youtube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com"));
                        startActivity(youtube);
                        break;

                    case "open spotify":
                        Intent spotify = new Intent(Intent.ACTION_VIEW, Uri.parse("spotify:"));
                        startActivity(spotify);
                        break;

                    default:
                        // Voice to Search Connector
                        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                        search.putExtra(SearchManager.QUERY, list.get(0));
                        startActivity(search);
                        break;
                }
            } catch (Exception e) {
                Toast.makeText(this, "Could not perform action: " + command, Toast.LENGTH_SHORT).show();
                // Fallback to search if the specific command failed
                Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
                search.putExtra(SearchManager.QUERY, list.get(0));
                startActivity(search);
            }
        } else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(this, "Speech recognition failed. Please try again.", Toast.LENGTH_SHORT).show();
        }

    }
}