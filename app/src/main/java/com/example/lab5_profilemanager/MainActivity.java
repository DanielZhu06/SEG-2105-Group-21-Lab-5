package com.example.lab5_profilemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView profilePic;
    EditText postalCode;

    private final ActivityResultLauncher<Intent> profileActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<androidx.activity.result.ActivityResult>() {
                @Override
                public void onActivityResult(androidx.activity.result.ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        int avatar = result.getData().getIntExtra("selected_avatar", -1);
                        if (avatar != -1) {
                            profilePic.setImageResource(avatar);
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profilePic = findViewById(R.id.avatarImage);
        postalCode = findViewById(R.id.postalCode);
        profilePic.setOnClickListener(v -> onSetAvatarButton(v));
    }


    public void onSetAvatarButton(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        profileActivityResultLauncher.launch(intent);
    }

    public void onOpenInGoogleMaps(View view) {
        String address = postalCode.getText().toString().trim();
        if (address.isEmpty()) {
            return;
        }
        Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        } else {
            Intent fallback = new Intent(Intent.ACTION_VIEW, mapUri);
            startActivity(fallback);
        }
    }
}
