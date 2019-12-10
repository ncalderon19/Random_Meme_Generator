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
                for (int i = 0; i < 1000; i++);
                setCaption();
                for (int i = 0; i < 1000; i++);
                newCap.setVisibility(View.VISIBLE);
            }
        });

        imageView = findViewById(R.id.imageView);
    }

    private void takePicFunction() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
        takePicture.setText("Take another picture");
    }

    private void setCaption() {
        String s = " 1 Did I make your heart skip a beat? 2 New look, same mistakes 3 Who’s a good boy? I am! 4 Life isn’t perfect. But my hair is! 5 Some people grow up, I glow up. 6 This is the most magical picture you’ll ever see 7 How much do I weigh? One hundred and sexy! 9 My dog dared me to take this picture\n" +
                "I think you are lacking vitamin ME! 10 Reality called, so I hung up. 11 I’ve got it, I’m flaunting it, and you’re liking it. 12 Fresher than you. 13 What do you think of the beautiful view? 14 When a CA responds to your forum post immediately. 15 How I feel after confidently dropping that last CBTF quiz 16 When Challen says no reading on the quiz 17 I woke up like this 8 Just a pic of me being an idiot";
        int length = 18;
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

    //Saving the picture
/**
    String photoPath;

    private File createImageFile() throws IOException {
        // Creating the file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // Creating the file
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        // Save a file: path for use with ACTION_VIEW intents
        photoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.println("oof!");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
    */
 }
