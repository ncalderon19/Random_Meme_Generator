package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageView imageView;
    Button takePicture;
    TextView cap;
    Button newCap;
    private static int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cap = findViewById(R.id.caption);
        cap.setVisibility(View.GONE);

        newCap = findViewById(R.id.newCaption);
        newCap.setVisibility(View.GONE);
        newCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCaption();
            }
        });

        takePicture = findViewById(R.id.takePicture);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicFunction();
                //for (int i = 0; i < 1000; i++);
                setCaption();
                //for (int i = 0; i < 1000; i++);
                setVisibilty();
            }
        });
        imageView = findViewById(R.id.imageView);
    }

    private void setVisibilty() {
        newCap.setVisibility(View.VISIBLE);
    }


    private void takePicFunction() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
        takePicture.setText("Take another picture");
    }

    private void setCaption() {
        String s = " 1 Did I make your heart skip a beat? 2 New look, same mistakes 3 Who’s a good boy? I am! 4 Life isn’t perfect. But my hair is! 5 Some people grow up, I glow up. 6 This is the most magical picture you’ll ever see 7 How much do I weigh? One hundred and sexy!\n" +
                "10 Reality called, so I hung up. 11 I’ve got it, I’m flaunting it, and you’re liking it. 12 Fresher than you. 13 What do you think of the beautiful view? 14 When a CA responds to your forum post immediately. 15 How I feel after confidently dropping that last CBTF quiz 16 When Challen says no reading on the quiz 9 I woke up like this 8 Just a pic of me being an idiot";
        int length = 16;
        int num = (int) (Math.random() * (length - 1) + 1);
        int numNext = num + 1;
        String realCappin;
        if (num == current) {
            num = (int) (Math.random() * (length - 1)) + 1;
        }
        if (num == length) {
            realCappin = s.substring((s.indexOf(num + "") + 2));
        } else {
            realCappin = s.substring((s.indexOf(num + "") + 2), s.indexOf(numNext + ""));
        }
        current = num;
        System.out.print(num);
        cap.setText(realCappin);
        cap.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
 }
