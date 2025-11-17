package com.example.lab5_profilemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Set click listeners on the flags to return the selected avatar
        setupFlag(R.id.flag_ca, R.drawable.flag_ca);
        setupFlag(R.id.flag_eg, R.drawable.flag_eg);
        setupFlag(R.id.flag_fr, R.drawable.flag_fr);
        setupFlag(R.id.flag_jp, R.drawable.flag_jp);
        setupFlag(R.id.flag_kr, R.drawable.flag_kr);
        setupFlag(R.id.flag_sp, R.drawable.flag_sp);
        setupFlag(R.id.flag_tr, R.drawable.flag_tr);
        setupFlag(R.id.flag_uk, R.drawable.flag_uk);
        setupFlag(R.id.flag_us, R.drawable.flag_us);
    }

    private void setupFlag(int viewId, int drawableId) {
        ImageView img = findViewById(viewId);
        img.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selected_avatar", drawableId);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
